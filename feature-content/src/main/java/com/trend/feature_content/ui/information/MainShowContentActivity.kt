package com.trend.feature_content.ui.information

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.trend.chevron.databinding.ActivityMainShowContentBinding
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.ui.home.ApplyUIMainContent
import com.trend.feature_content.ui.home.ApplyUIMainContentImpl
import com.trend.feature_tests.ui.MainExamenActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

@AndroidEntryPoint
class MainShowContentActivity : AppCompatActivity(), ApplyUIMainContent by ApplyUIMainContentImpl() {
    private lateinit var binding: ActivityMainShowContentBinding

    private val viewModel: MainShowContentViewModel by viewModels()

    private var BODY1: String = ""
    private var BODY2: String = ""
    private var PATH_AUDIO: String = ""
    private var PATH_IMAGE: String = ""
    private var VALOR : Int = 0
    private var TITLE: String = ""
    private var PARADA: String = ""
    private var TYPE_ACCOUNT: TypeAccount = TypeAccount.HAVOLINE
    private var IDSTOP: Int = 0
    private var IDUSER: Int = 0
    private var STATUSLESSON: Int = 0
    private var IDROUTE: Int = 0

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainShowContentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtras()
        initialSetup()
        setupWebView()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {

        lifecycleScope.launch {
            viewModel.update.collect {
                when(it) {
                    is BaseEvent.Init -> {}
                    is BaseEvent.Loading -> ProgressUtil.showLoading(this@MainShowContentActivity)
                    is BaseEvent.Success -> {
                        ProgressUtil.hideLoading()
                        when(VALOR){
                            1 -> finish()
                            2 -> finish()
                            3 -> {
                                startAnotherActivity()
                            }
                            else -> finish()
                        }

                    }
                    is BaseEvent.Error -> ProgressUtil.hideLoading()
                }
            }
        }
    }

    private fun setupListeners() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.webLayout.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, weburl: String) {
                ProgressUtil.hideLoading()
            }
        }
        binding.endButton.setOnClickListener {
            binding.endButton.isEnabled = false
            binding.endButton.isFocusable = false
            ProgressUtil.showLoading(this@MainShowContentActivity)
            viewModel.updateStatusLesson(StatusLessonsRequest(IDUSER, IDSTOP, VALOR))
        }
    }

    private fun startAnotherActivity() {
        startActivity(Intent(this, FinishLessonActivity::class.java).apply {
            putExtra(constants.TYPE_ACCOUNT, TYPE_ACCOUNT)
            putExtra(constants.IDROUTE, IDROUTE)
            putExtra(constants.PARADA, PARADA)
        })
        finish()
    }

    private fun getExtras() {
        intent.extras?.let {
            BODY1 = it.getString(constants.BODY1).toString()
            BODY2 = it.getString(constants.BODY2).toString()
            PATH_AUDIO = it.getString(constants.PATH_AUDIO).toString()
            PATH_IMAGE = it.getString(constants.PATH_IMAGE).toString()
            VALOR = it.getInt(constants.VALUE_DATA)
            TITLE = it.getString(constants.TITLE).toString()
            PARADA = it.getString(constants.PARADA).toString()
            TYPE_ACCOUNT = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
            IDSTOP = it.getInt(constants.IDSTOP)
            IDUSER = it.getInt(constants.IDUSER)
            STATUSLESSON = it.getInt(constants.STATUSLESSON)
            IDROUTE = it.getInt(constants.IDROUTE)
        }
    }

    private fun setupWebView() {
        binding.webLayout.settings.useWideViewPort = true
        binding.webLayout.settings.loadWithOverviewMode = true
        binding.webLayout.settings.javaScriptEnabled = false
        binding.webLayout.settings.allowFileAccess = true
        binding.webLayout.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        binding.textRoute.text = "Ruta " + IDROUTE
        binding.paradaName.text = PARADA
    }

    private fun initialSetup() {

        applyUIDisplayContent(
            context = this@MainShowContentActivity,
            typeAccount = TYPE_ACCOUNT,
            binding = binding
        )

        when(VALOR) {
            1 -> showOption1And2(BODY1)
            2 -> showOption1And2(BODY2)
            3 -> {
                ProgressUtil.hideLoading()
                if(STATUSLESSON == 1) binding.endButton.visibility = View.GONE
                Glide.with(this).load(PATH_IMAGE).into(binding.imageCharacter)

                binding.btnPlayAudio.visibility = View.VISIBLE
                binding.btnStopAudio.visibility = View.VISIBLE
                binding.titleAudio.text = TITLE

                PATH_AUDIO.let {
                    try {
                        mediaPlayer.setAudioAttributes(
                            AudioAttributes.Builder()
                                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                                .build())
                        mediaPlayer.setDataSource(this, Uri.parse(PATH_AUDIO))
                        mediaPlayer.prepareAsync()
                        mediaPlayer.setVolume(1f, 1f)
                        mediaPlayer.isLooping = false

                    } catch (e: IllegalArgumentException) {
                        e.printStackTrace();
                    } catch (e: IllegalStateException) {
                        e.printStackTrace();
                    } catch (e: IOException) {
                        e.printStackTrace();
                    }
                }

                PATH_AUDIO.takeIf { it.isNotEmpty() }?.let {
                    binding.btnPlayAudio.setOnClickListener {
                        try {
                            mediaPlayer.start()
                        } catch (ex: Exception) {
                            Snackbar.make(this@MainShowContentActivity, binding.displayMainContainer, ex.message.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    }
                } ?: run {
                    Snackbar.make(this@MainShowContentActivity, binding.displayMainContainer, "No se ha encontrado una ruta válida de audio para reproducción.", Snackbar.LENGTH_LONG).show()
                    return
                }

                binding.btnStopAudio.setOnClickListener {
                    try {
                        mediaPlayer.let {
                            mediaPlayer.takeIf { it.isPlaying }?.also {
                                it.pause()
                            }
                        }
                    } catch(ex: Exception) {

                    }
                }
            }
        }
    }

    private fun showOption1And2(body: String) {
        ProgressUtil.showLoading(this@MainShowContentActivity)
        binding.webLayout.visibility = View.VISIBLE
        if(STATUSLESSON == 1) binding.endButton.visibility = View.GONE
        binding.webLayout.loadDataWithBaseURL(null, body, "text/html", "utf-8", null)
    }

    override fun onPause() {
        mediaPlayer.let { media ->
            media.takeIf { it.isPlaying }?.also {
                it.stop()
                it.release()
            }
        }
        super.onPause()
    }
}