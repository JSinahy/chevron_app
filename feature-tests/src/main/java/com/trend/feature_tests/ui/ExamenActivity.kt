package com.trend.feature_tests.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivityExamenBinding
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.CorrectAndIncorrectAnswers
import com.trend.feature_common.models.TestModel
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.DrawableUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExamenActivity : AppCompatActivity(),
    ApplyUITestContent by ApplyUITestContentImpl(),
    ApplyDataTestDelegate by ApplyDataTestDelegateImpl(){

    private lateinit var binding: ActivityExamenBinding
    val viewModel: ExamenViewModel by viewModels()

    var typeAccount: TypeAccount = TypeAccount.HAVOLINE
    companion object {
        var checked = false
        var corrects: Int = 0
    }

    var index: Int = 0
    var questions = ArrayList<TestModel>()

    var IdRoute: Int = 0
    var IdStop: Int = 0
    var IdTest: Int = 0
    var IsLastOne: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()
        setupInitial()
        setupUITests()
        setupObservers()
        setupListeners()
    }

    private fun setupInitial() {
        corrects = 0
        checked = false
        viewModel.getTests(TestRequest(IdTest))
    }

    private fun getExtras() {
        intent.extras?.let {
            IdRoute = it.getInt(constants.IDROUTE)
            IdStop = it.getInt(constants.IDSTOP)
            IdTest = it.getInt(constants.IDTEST)
            typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
            IsLastOne = it.getBoolean(constants.ISLASTONE)
        }
    }

    private fun setupUITests() {
        applyUITestApplication(
            this,
            binding,
            typeAccount
        )
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.tests.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> ProgressUtil.showLoading(this@ExamenActivity)
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        questions = it.data.test
                        setupFirstQuestion()
                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                    }
                }
            }
        }
    }

    private fun setupFirstQuestion() {
        updateDataUI(index, questions, binding, typeAccount)
    }

    private fun setupListeners() {
        binding.btnCheck.setOnClickListener {
            if(!checked) {
                updateCorrectIncorrectUI(this, binding, CorrectAndIncorrectAnswers.answers.result, CorrectAndIncorrectAnswers.answers.selected)
                binding.btnCheck.text = "Siguiente"
                setButtonColor(typeAccount)
                checked = true
            } else {
                checked = false
                setButtonColor(typeAccount)
                binding.btnCheck.text = "Comprobar"
                if(index < questions.size) {
                    Log.wtf("QUESTIONS: ", "" + questions.size)
                    Log.wtf("INDEX ANTES: ", "" + index)
                    if(index == questions.size -1){
                        startOtherActivity()
                    }
                    index++
                    Log.wtf("INDEX DESPUES: ", "" + index)
                    if(index < questions.size)
                        updateDataUI(index,questions, binding, typeAccount)

                } else {
                    startOtherActivity()
                }
            }
        }
    }

    private fun setButtonColor(typeAccount: TypeAccount) {
        when(typeAccount) {
            TypeAccount.TEXACO -> {
                binding.btnCheck.background = AppCompatResources.getDrawable(this, DrawableUtils.getButtonBlackWithYellowBorder())
                binding.btnCheck.setTextColor(Color.parseColor("#FFFFFF"))
            }
            TypeAccount.HAVOLINE -> { binding.btnCheck.background = AppCompatResources.getDrawable(this, DrawableUtils.getButtonBlackWithYellowBorder()) }
            TypeAccount.HAVOLINE4T -> {
                binding.btnCheck.background = AppCompatResources.getDrawable(this, DrawableUtils.getButtonDarkRedWithYellowBorder())
                binding.btnCheck.setTextColor(Color.parseColor("#FFFFFF"))
            }
            TypeAccount.DELO -> {
                binding.btnCheck.background = AppCompatResources.getDrawable(this, DrawableUtils.getButtonBlue())
                binding.btnCheck.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private fun startOtherActivity() {
        IsLastOne.takeIf { !it }?.also {
            startActivity(Intent(this@ExamenActivity, ExamenResultActivity::class.java).apply {
                putExtra(constants.IDSTOP, IdStop)
                putExtra(constants.IDTEST, IdTest)
                putExtra(constants.TYPE_ACCOUNT, typeAccount)
                putExtra(constants.CALIFICATION, corrects)
            })
        } ?: run {
            startActivity(Intent(this@ExamenActivity, ShareExamenResultActivity::class.java).apply {
                putExtra(constants.IDROUTE, IdRoute)
                putExtra(constants.IDSTOP, IdStop)
                putExtra(constants.IDTEST, IdTest)
                putExtra(constants.TYPE_ACCOUNT, typeAccount)
                putExtra(constants.CALIFICATION, corrects)
            })
        }
        finish()
    }


}