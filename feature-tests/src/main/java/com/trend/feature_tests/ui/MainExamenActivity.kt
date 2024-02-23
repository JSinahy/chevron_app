package com.trend.feature_tests.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trend.chevron.databinding.ActivityMainExamenBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.extensiones.getCardinalNumber

class MainExamenActivity : AppCompatActivity(), ApplyUITestContent by ApplyUITestContentImpl() {
    private lateinit var binding: ActivityMainExamenBinding

    private var IdRoute: Int = 0
    private var IdStop: Int = 0
    private var IdTest: Int = 0
    var typeAccount: TypeAccount = TypeAccount.HAVOLINE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainExamenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupExtras()
        setupRoutes()
        setupUI()
        setupListener()
    }

    private fun setupUI() {
        applyMainTestContent(
            typeAccount,
            binding
        )
    }

    private fun setupRoutes() {
        binding.labelRoute.text = "Ruta " + IdRoute.toString()
        binding.textWelcome.text = "Bienvenido a tu\n" + IdRoute.getCardinalNumber() + " prueba\nExperto"
    }

    private fun setupExtras() {
        intent.extras?.let {
            IdRoute = it.getInt(constants.IDROUTE)
            IdStop = it.getInt(constants.IDSTOP)
            IdTest = it.getInt(constants.IDTEST)
            typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
        }
    }

    private fun setupListener() {
        binding.nextButton.setOnClickListener {
            val mIntent = Intent(this, ExamenActivity::class.java).apply {
                    putExtra(constants.IDROUTE, IdRoute)
                    putExtra(constants.IDSTOP, IdStop)
                    putExtra(constants.IDTEST, IdTest)
                    putExtra(constants.TYPE_ACCOUNT, typeAccount)
                    putExtra(constants.ISLASTONE, IdRoute == 6)
                }
            startActivity(mIntent)
            finish()
        }
    }
}
