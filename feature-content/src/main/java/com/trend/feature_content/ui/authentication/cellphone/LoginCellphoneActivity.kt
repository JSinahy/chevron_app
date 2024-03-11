package com.trend.feature_authentication.ui.cellphone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.trend.feature_authentication.ui.otp.LoginOTPActivity
import com.trend.feature_common.delegates.ApplyLoadLocalDropdown
import com.trend.feature_common.delegates.ApplyLoadLocalDropdownImpl
import com.trend.feature_common.delegates.ApplySharedPreferences
import com.trend.feature_common.delegates.ApplySharedPreferencesImpl
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.CountriesCellphoneDataModel
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_common.utils.DetectTypeAccount
import com.trend.feature_content.databinding.ActivityLoginCellphoneBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginCellphoneActivity : AppCompatActivity(),
    ApplyLoadLocalDropdown by ApplyLoadLocalDropdownImpl(),
    ApplySharedPreferences by ApplySharedPreferencesImpl() {

    private lateinit var binding: ActivityLoginCellphoneBinding
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginCellphoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadLocalDropdownCountries()
        setupObservers()
        setupListeners()
    }

    private fun loadLocalDropdownCountries() {
        applyLoadCountryPhone(
            context = this@LoginCellphoneActivity,
            spnCountries = binding.spnCountries
        )
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.login.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> ProgressUtil.showLoading(this@LoginCellphoneActivity)
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        applyUpdateSharedPreferences(this@LoginCellphoneActivity, constants.CUSTOM_PREF_NAME, it.data.data)
                        startOTPActivity(it.data)
                    }
                    is BaseEvent.Error -> {
                        Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                        ProgressUtil.hideLoading()
                    }
                }
            }
        }
    }

    private fun startOTPActivity(data: LoginResponse) {
        startActivity(Intent(this, LoginOTPActivity::class.java).apply {
            putExtra(constants.TYPE_ACCOUNT, DetectTypeAccount.getTypeAccount(data.data.ch_IdEnterprise))
            putExtra(constants.ID_ENTERPRISE, data.data.ch_IdEnterprise)
            putExtra(constants.ID_USER, data.data.ch_Id)
            putExtra(constants.COUNTRY_CODE, data.data.ch_CountryCode)
            putExtra(constants.CELLPHONE, data.data.ch_Phone)
            putExtra(constants.NAMEUSER, data.data.ch_Name + " " + data.data.ch_LastName)
            putExtra(constants.STUDYTIME, data.data.ch_StudyTime)
            putExtra(constants.OTP, data.otp)
        })
        finish()
    }

    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            val lada = binding.spnCountries.selectedItem as CountriesCellphoneDataModel
            viewModel.loginWithCellNumber(LoginModel("${binding.textCellphone.text}", lada.code))
        }
    }
}