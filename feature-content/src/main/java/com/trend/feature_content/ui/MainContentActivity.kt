package com.trend.feature_content.ui

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.utils.AlertDialogHelper
import com.trend.feature_common.utils.DetectTypeAccount
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.clearValues
import com.trend.feature_common.utils.PreferencesHelper.isLogged
import com.trend.feature_common.utils.PreferencesHelper.lastNameUser
import com.trend.feature_common.utils.PreferencesHelper.nameUser
import com.trend.feature_common.utils.PreferencesHelper.timeToStudy
import com.trend.feature_common.utils.PreferencesHelper.typeAccount
import com.trend.feature_content.databinding.ActivityMainContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainContentActivity : AppCompatActivity(), ApplyUIMainContentDelegate by ApplyUIMainContentDelegateImpl() {

    private lateinit var prefs: SharedPreferences
    private var typeAccount = TypeAccount.HAVOLINE
    private lateinit var binding: ActivityMainContentBinding
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferencesHelper.customPreference(this, constants.CUSTOM_PREF_NAME)
        binding = ActivityMainContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.includeMainContent.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        applyUIMainContent()
        setupPressCallback()
        setupListener()
        initSetup()
    }

    private fun setupListener() {
        binding.includeSidebarContent.buttonCloseSession.setOnClickListener {
            AlertDialogHelper.basic(this,
                "Chevron App",
                "¿Desea cerrar su sesión y salir de la aplicación?",
                "SI",
                "NO",
                { _: DialogInterface, _: Int ->
                    run {
                        prefs.isLogged = false
                        prefs.clearValues
                        finish()
                    }
                },
                { _: DialogInterface, _: Int -> 0},
                false
            )
        }
    }

    private fun applyUIMainContent() {
        typeAccount = DetectTypeAccount.getTypeAccount(prefs.typeAccount.toString())
        applyUISideBar(
            binding = binding,
            typeAccount = typeAccount
        )
    }

    private fun initSetup() {
        binding.includeMainContent.toolbar.title = " "
        binding.apply {
            actionBarToggle = ActionBarDrawerToggle(this@MainContentActivity, drawerLayout, binding.includeMainContent.toolbar, 0, 0)
            drawerLayout.addDrawerListener(actionBarToggle)
            actionBarToggle.syncState()
        }
        binding.includeSidebarContent.textName.text = prefs.nameUser + "\n" + prefs.lastNameUser
        binding.includeSidebarContent.textTime.text =prefs.timeToStudy.toString() + " min"
    }
    private fun setupPressCallback() {
        callback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    AlertDialogHelper.basic(this@MainContentActivity,
                        "Experto Chevron",
                        "¿Desea salir de la aplicación?",
                        "SI",
                        "NO",
                        { _: DialogInterface, _: Int ->
                            run {
                                finish()
                            }
                        },
                        { _: DialogInterface, _: Int -> 0},
                        false
                    )
                }
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }


}