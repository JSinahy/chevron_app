package com.trend.feature_tests.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.setGrayScaleColor
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_test.R
import com.trend.feature_test.databinding.ActivitySelectProfileTestBinding
import kotlinx.coroutines.launch

class SelectProfileTestActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySelectProfileTestBinding
    private val viewModel: SelectProfileTestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectProfileTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()


    }

    private fun setupListener() {
        binding.imageHavoline.setOnClickListener(this)
        binding.imageHavoline4t.setOnClickListener(this)
        binding.imageDelo.setOnClickListener(this)
        binding.imageTexaco.setOnClickListener(this)

    }

    private fun setupObservers() {
        /*lifecycleScope.launch {
            viewModel.getRoadmapCompleted(1)
            viewModel.trends.collect {
                when(it){
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> { ProgressUtil.showLoading(this@SelectProfileTestActivity )}
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        it.data.data.forEach {item ->
                            if(item.ch_Passed == 1) {
                                when(item.ch_IdEnterprise) {
                                    1 -> { binding.imageHavoline.setGrayScaleColor() }
                                    2 -> { binding.imageHavoline4t.setGrayScaleColor() }
                                    3 -> { binding.imageDelo.setGrayScaleColor() }
                                    4 -> { binding.imageTexaco.setGrayScaleColor() }
                                }
                            }
                        }
                    }
                    is BaseEvent.Error -> {
                        ProgressUtil.hideLoading()
                    }
                }
            }
        }*/
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.imageHavoline -> openCreateAccountActivity(TypeAccount.HAVOLINE)
            R.id.imageHavoline4t -> openCreateAccountActivity(TypeAccount.HAVOLINE4T)
            R.id.imageDelo -> openCreateAccountActivity(TypeAccount.DELO)
            R.id.imageTexaco -> openCreateAccountActivity(TypeAccount.TEXACO)
        }
    }

    fun openCreateAccountActivity(type: TypeAccount) {


    }
}