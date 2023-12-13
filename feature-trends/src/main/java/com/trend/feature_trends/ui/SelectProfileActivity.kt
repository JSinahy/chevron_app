package com.trend.feature_trends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivitySelectProfileBinding

class SelectProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}