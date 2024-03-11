package com.trend.feature_content.ui.information

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_content.databinding.ActivityFinishLessonBinding
import com.trend.feature_content.ui.home.ApplyUIMainContent
import com.trend.feature_content.ui.home.ApplyUIMainContentImpl

class FinishLessonActivity : AppCompatActivity(), ApplyUIMainContent by ApplyUIMainContentImpl() {

    private lateinit var binding: ActivityFinishLessonBinding
    private var TYPE_ACCOUNT: TypeAccount = TypeAccount.HAVOLINE
    private var IDROUTE: Int = 0
    private var PARADA: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishLessonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCatchValues()
        setupListeners()
        setupUI()
    }

    private fun setupListeners() {
        binding.returnButton.setOnClickListener {
            finish()
        }
    }

    fun initCatchValues() {
        intent.extras?.let {
            TYPE_ACCOUNT = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
            IDROUTE = it.getInt(constants.IDROUTE)
            PARADA = it.getString(constants.PARADA).toString()
        }
        binding.textRoute.text = "Ruta " + IDROUTE
        binding.paradaName.text = PARADA
    }

    private fun setupUI() {
        applyUIFinishLessonContent(
            TYPE_ACCOUNT,
            binding
        )
    }
}