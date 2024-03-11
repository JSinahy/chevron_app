package com.trend.feature_content.ui.examenes

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.idUser
import com.trend.feature_common.utils.PreferencesHelper.isLogged
import com.trend.feature_content.databinding.ActivityExamenResultBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExamenResultActivity : AppCompatActivity(), ApplyUITestContent by ApplyUITestContentImpl() {

    lateinit var binding: ActivityExamenResultBinding
    var califications: Int = 0
    var typeAccount: TypeAccount = TypeAccount.HAVOLINE
    private val viewModel: ExamenResultViewModel by viewModels()
    private lateinit var prefs: SharedPreferences
    private var _ID_USER_: Int = 0
    private var IdStop: Int = 0
    private var IdRoute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExamenResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()
        setPrefs()
        setupListener()
        setupUIResults()
        saveMyProgressTest()
    }

    private fun getExtras() {
        intent.extras?.let {
            IdStop = it.getInt(constants.IDSTOP)
            IdRoute = it.getInt(constants.IDROUTE)
            califications = it.getInt(constants.CALIFICATION)
            typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
        }
    }

    private fun setPrefs() {
        prefs = PreferencesHelper.customPreference(this, constants.CUSTOM_PREF_NAME)
        if(prefs.isLogged) {
            _ID_USER_ = prefs.idUser
        }
    }

    private fun setupListener(){
        binding.nextButton.setOnClickListener {
            finish()
        }
    }

    private fun setupUIResults() {
        applyUITestResults(
            this@ExamenResultActivity,
            binding,
            califications,
            typeAccount,
            IdRoute
        )
    }

    private fun saveMyProgressTest() {
        lifecycleScope.launch {
            viewModel.update.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> { ProgressUtil.showLoading(this@ExamenResultActivity)}
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()

                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                        Snackbar.make(this@ExamenResultActivity, binding.mainLayout, it.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
        viewModel.updateStatusLessons(StatusLessonsRequest(_ID_USER_, IdStop, 4))
    }


}