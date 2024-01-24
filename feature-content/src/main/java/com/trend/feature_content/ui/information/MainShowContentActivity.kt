package com.trend.feature_content.ui.information

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
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.trend.chevron.databinding.ActivityMainShowContentBinding
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundRedTexture
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setCharacterDelo
import com.trend.feature_common.extensiones.setCharacterHavoline4T
import com.trend.feature_common.extensiones.setCharacterTexaco
import com.trend.feature_common.extensiones.setDeloButton
import com.trend.feature_common.extensiones.setHavoline4TButton
import java.io.IOException

class MainShowContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainShowContentBinding

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
        setBackground()
        setupListeners()
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

    private fun setBackground() {
        when (TYPE_ACCOUNT) {
            TypeAccount.TEXACO -> binding.imgBackgroun.setBackgroundRedTexture()
            TypeAccount.DELO -> binding.imgBackgroun.visibility = View.GONE
            TypeAccount.HAVOLINE4T -> binding.imgBackgroun.setBackgroundRedTexture()
            else -> binding.imgBackgroun.setBackgroundHavoline()
        }
    }

    private fun initialSetup() {
        when(TYPE_ACCOUNT) {
            TypeAccount.TEXACO -> {
                binding.imageCharacter.setCharacterTexaco()
                binding.imgBackgroun.setBackgroundRedTexture()
                binding.toolbarInfo.setBackgroundTexaco()
            }
            TypeAccount.DELO -> {
                binding.imageCharacter.setCharacterDelo()
                binding.displayMainContainer.setBackgroundDelo()
                binding.imgBackgroun.visibility = View.GONE
                binding.toolbarInfo.setBackgroundDelo()
                binding.endButton.setDeloButton()
            }
            TypeAccount.HAVOLINE4T -> {
                binding.imageCharacter.setCharacterHavoline4T()
                binding.imgBackgroun.setBackgroundHavoline4t()
                binding.toolbarInfo.setBackgroundTexaco()
                binding.endButton.setHavoline4TButton()
            }
            else -> {}
        }

        when(VALOR) {
            1 -> {
                ProgressUtil.showLoading(this@MainShowContentActivity)
                binding.webLayout.visibility = View.VISIBLE
                if(STATUSLESSON == 1) binding.endButton.visibility = View.GONE
                BODY1.let {
                    binding.webLayout.loadDataWithBaseURL(null, it, "text/html", "utf-8", null)
                }
            }
            2 -> {
                ProgressUtil.showLoading(this@MainShowContentActivity)
                binding.webLayout.visibility = View.VISIBLE
                if(STATUSLESSON == 1) binding.endButton.visibility = View.GONE
                BODY2.let {
                    binding.webLayout.loadDataWithBaseURL(null, it, "text/html", "utf-8", null)
                }
            }
            3 -> {
                setBackground()
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