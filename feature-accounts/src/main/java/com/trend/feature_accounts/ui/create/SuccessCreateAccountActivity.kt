package com.trend.feature_accounts.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trend.feature_accounts.databinding.ActivitySuccessCreateAccountBinding
import com.trend.feature_authentication.ui.cellphone.LoginCellphoneActivity
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.openActivity

class SuccessCreateAccountActivity : AppCompatActivity(),
    ApplyUiTypeAccount by ApplyUiTypeAccountImpl() {
    private lateinit var binding: ActivitySuccessCreateAccountBinding
    private lateinit var typeAccount: TypeAccount

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessCreateAccountBinding.inflate(layoutInflater)
        typeAccount = TypeAccount.TEXACO
        setContentView(binding.root)
        setUiTypeAccountSuccess()
        setupListeners()
    }

    private fun setUiTypeAccountSuccess() {
        typeAccount = TypeAccount.TEXACO
        applyUITypeAccountSuccess(
            binding,
            typeAccount
        )
    }

    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            this.openActivity(LoginCellphoneActivity::class.java, true)
        }
    }
}