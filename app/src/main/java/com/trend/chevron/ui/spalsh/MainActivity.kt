package com.trend.chevron.ui.spalsh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.trend.chevron.R
import com.trend.chevron.data.openActivity
import com.trend.chevron.databinding.ActivityMainBinding
import com.trend.feature_trends.ui.SelectProfileActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.btnSignin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.btnSignin -> {

            }
            binding.btnSignup -> {
                this.openActivity(SelectProfileActivity::class.java)
            }
        }
    }


}