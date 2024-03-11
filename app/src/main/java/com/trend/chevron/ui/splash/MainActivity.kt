package com.trend.chevron.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.trend.chevron.databinding.ActivityMainBinding
import com.trend.feature_authentication.ui.cellphone.LoginCellphoneActivity
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.extensiones.openActivity
import com.trend.feature_common.utils.Activities
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.isLogged
import com.trend.feature_content.ui.MainContentActivity
import com.trend.feature_content.ui.profiles.SelectProfileActivity


class MainActivity : AppCompatActivity(), OnClickListener, Activities {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        automaticLogIn()
    }

    private fun initListener() {
        binding.btnSignin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)
    }

    private fun automaticLogIn() {
        val prefs = PreferencesHelper.customPreference(this, constants.CUSTOM_PREF_NAME)
        if(prefs.isLogged){
            startActivity(Intent(this, MainContentActivity::class.java))
            finish()
        }
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.btnSignin -> {
                this.openActivity(LoginCellphoneActivity::class.java)
            }
            binding.btnSignup -> {
                this.openActivity(SelectProfileActivity::class.java)
            }
        }
    }

    override fun open() {
        startActivity(Intent(this@MainActivity, SelectProfileActivity::class.java))
    }


}