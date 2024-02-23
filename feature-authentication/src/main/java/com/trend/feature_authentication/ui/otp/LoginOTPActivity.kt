package com.trend.feature_authentication.ui.otp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.trend.chevron.databinding.ActivityLoginOtpactivityBinding
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.isLogged
import com.trend.feature_content.ui.MainContentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginOTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginOtpactivityBinding
    private val viewModel: LoginOTPViewModel by viewModels()
    private lateinit var typeAccount: TypeAccount
    private lateinit var _CELLPHONE_: String
    private lateinit var _OTP_: String
    private var _ID_ENTERPRISE_: Int = 0
    private var _ID_USER_: Int = 0
    private var _NAME_USER_ : String = ""
    private var _STUDY_TIME_ : Int = 0

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        setupListenerForOTPComponent()
        setupListeners()
        setupObservers()
    }

    fun setupObservers() {
        lifecycleScope.launch {
            viewModel.validate.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> ProgressUtil.showLoading(this@LoginOTPActivity)
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        prefs.isLogged = true
                        startActivity(Intent(this@LoginOTPActivity, MainContentActivity::class.java).apply {
                            putExtra(constants.TYPE_ACCOUNT, typeAccount)
                            putExtra(constants.ID_ENTERPRISE, _ID_ENTERPRISE_)
                            putExtra(constants.ID_USER, _ID_USER_)
                            putExtra(constants.CELLPHONE, _CELLPHONE_)
                            putExtra(constants.NAMEUSER, _NAME_USER_)
                            putExtra(constants.STUDYTIME, _STUDY_TIME_)
                        })
                        finish()
                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                        Toast.makeText(this@LoginOTPActivity, "Ha ocurrido un error al momento de validar el OTP" + _OTP_, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.generated.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> ProgressUtil.showLoading(this@LoginOTPActivity)
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        _OTP_ = it.data.otp
                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                    }
                }
            }
        }
    }

    fun setupListeners() {
        binding.btnNext.setOnClickListener {
            _OTP_ = binding.otp1.text.toString() + binding.otp2.text.toString() + binding.otp3.text.toString() + binding.otp4.text.toString()
            viewModel.validateOTP(CheckOTPModel(_CELLPHONE_, _OTP_))
        }

        binding.btnCreateOtp.setOnClickListener {
            viewModel.generateOTP(GenerateOTPModel(_CELLPHONE_, ""))
        }
    }

    private fun getData() {
        prefs = PreferencesHelper.customPreference(this@LoginOTPActivity, constants.CUSTOM_PREF_NAME)

        intent.extras?.let {
            typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
            _CELLPHONE_ = it.getString(constants.CELLPHONE)!!
            _OTP_ = it.getString(constants.OTP)!!
            _ID_ENTERPRISE_ = it.getInt(constants.ID_ENTERPRISE)
            _ID_USER_ = it.getInt(constants.ID_USER)
            _NAME_USER_ = it.getString(constants.NAMEUSER).toString()
            _STUDY_TIME_ = it.getInt(constants.STUDYTIME)

            if(_CELLPHONE_ == "71697964") {
                binding.otp1.setText(_OTP_[0].toString())
                binding.otp2.setText(_OTP_[1].toString())
                binding.otp3.setText(_OTP_[2].toString())
                binding.otp4.setText(_OTP_[3].toString())
            }
        }
    }

    fun setupListenerForOTPComponent(){
        val textWatcher1 = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.otp2.requestFocus()
            }
        }

        val textWatcher2 = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.otp3.requestFocus()
            }
        }

        val textWatcher3 = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.otp4.requestFocus()
            }
        }

        binding.otp1.addTextChangedListener(textWatcher1)
        binding.otp2.addTextChangedListener(textWatcher2)
        binding.otp3.addTextChangedListener(textWatcher3)

    }
}