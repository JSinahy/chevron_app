package com.trend.feature_content.ui.examenes

import android.content.ComponentName
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.share.Sharer
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareDialog
import com.trend.feature_common.extensiones.ProgressUtil
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.utils.EnterprisesUtils
import com.trend.feature_common.utils.PreferencesHelper
import com.trend.feature_common.utils.PreferencesHelper.idEnterprise
import com.trend.feature_common.utils.PreferencesHelper.idUser
import com.trend.feature_content.databinding.ActivityShareExamenResultBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL


class ShareExamenResultActivity() : AppCompatActivity(),
    ApplyUITestContent by ApplyUITestContentImpl() {
    lateinit var callbackManager: CallbackManager
    private lateinit var binding: ActivityShareExamenResultBinding

    var IdRoute: Int = 0
    var IdStop: Int = 0
    var IdTest: Int = 0
    var typeAccount: TypeAccount = TypeAccount.HAVOLINE
    private lateinit var prefs: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = PreferencesHelper.customPreference(this, constants.CUSTOM_PREF_NAME)
        binding = ActivityShareExamenResultBinding.inflate(layoutInflater)
        callbackManager = CallbackManager.Factory.create()
        setContentView(binding.root)
        getExtras()
        initSetup()
        //setupImage()
        //guardarImagenEnDescargas()
        setupListeners()
    }

    fun initSetup() {
        applyUICertificateResults(
            this@ShareExamenResultActivity,
            binding,
            typeAccount
        )
    }

    fun guardarImagenEnDescargas() {
        lifecycleScope.launch(Dispatchers.IO) {
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                EnterprisesUtils.getName(prefs.idEnterprise)+"_"+prefs.idUser+".png"
            )
            val bytes =
                URL("https://expertochevron.com/resources/certificates/" + EnterprisesUtils.getName(prefs.idEnterprise) + "/" + prefs.idUser).readBytes()
            file.writeBytes(bytes)
        }
    }

    private fun setupListeners() {

        binding.shareWithFacebook.setOnClickListener {
            ProgressUtil.showLoading(this@ShareExamenResultActivity)
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "images_certificate.png"
            )
            val bmOptions: BitmapFactory.Options = BitmapFactory.Options()

            val bitmap = BitmapFactory.decodeFile(file.absolutePath, bmOptions)

            val shareDialog = ShareDialog(this@ShareExamenResultActivity)

            val photoShared = SharePhoto.Builder()
                .setBitmap(bitmap)
                .build()
            if(ShareDialog.canShow(SharePhotoContent::class.java)){
                val photoContent = SharePhotoContent.Builder()
                    .addPhoto(photoShared)
                    .build()
                shareDialog.registerCallback(callbackManager, object: FacebookCallback<Sharer.Result> {
                    override fun onCancel() {
                        ProgressUtil.hideLoading()
                    }

                    override fun onError(error: FacebookException) {
                        ProgressUtil.hideLoading()
                    }

                    override fun onSuccess(result: Sharer.Result) {
                        ProgressUtil.hideLoading()
                    }

                })
                shareDialog.show(photoContent)
            }
        }

        binding.keepLearning.setOnClickListener {
            val intent =  Intent(Intent.ACTION_MAIN)
            intent.setComponent(ComponentName("com.trend.feature_trends","com.trend.feature_trends.ui.SelectProfileActivity")).apply {
                putExtra(constants.FROM, 1)
                putExtra(constants.TYPE_ACCOUNT, typeAccount)
            }
            startActivity(intent)
        }
    }

    private fun setupImage() {
        ProgressUtil.showLoading(this@ShareExamenResultActivity)
        val url = "https://expertochevron.com/resources/certificates/" + EnterprisesUtils.getName(prefs.idEnterprise)  + "/" + prefs.idUser
        Glide.with(this@ShareExamenResultActivity)
            .load(url)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    ProgressUtil.hideLoading()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    ProgressUtil.hideLoading()
                    return false
                }

            })
            .into(binding.imgCertificate)
    }

    private fun getExtras() {
        intent.extras?.let {
            IdRoute = it.getInt(constants.IDROUTE)
            IdStop = it.getInt(constants.IDSTOP)
            IdTest = it.getInt(constants.IDTEST)
            typeAccount = it.getSerializable(constants.TYPE_ACCOUNT) as TypeAccount
        }
    }


}