package com.trend.feature_trends.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivitySelectProfileBinding
import com.trend.feature_accounts.ui.create.CreateAccountActivity
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.extensiones.openActivity
import com.trend.feature_common.permissions.PermissionsHelper

class SelectProfileActivity : AppCompatActivity(), View.OnClickListener {

    private var permissionList = listOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var binding: ActivitySelectProfileBinding
    private val PermissionsRequestCode = 123
    private lateinit var managePermissions: PermissionsHelper
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val listCountries = listOf("EL SALVADOR","GUATEMALA","HONDURAS","PANAMA", "COLOMBIA")

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectProfileBinding.inflate(layoutInflater)
        managePermissions = PermissionsHelper(this@SelectProfileActivity, permissionList, PermissionsRequestCode)

        setContentView(binding.root)
        setupListener()
        setUrlForDocumentation()
        setupPermissions()
    }

    private fun setupListener() {
        binding.imageHavoline.setOnClickListener(this)
        binding.imageHavoline4t.setOnClickListener(this)
        binding.imageDelo.setOnClickListener(this)
        binding.imageTexaco.setOnClickListener(this)
        managePermissions.checkPermissions()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("MissingPermission")
    private fun setupPermissions() {
        if(managePermissions.isPermissionsGranted() == 0) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.getCurrentLocation(LocationRequest.QUALITY_BALANCED_POWER_ACCURACY, object: CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
                override fun isCancellationRequested() = false
            }).addOnSuccessListener {location: Location ->
                if(location == null) {
                    Toast.makeText(this@SelectProfileActivity, "", Toast.LENGTH_LONG).show()
                } else {
                    val geocoder = Geocoder(this@SelectProfileActivity)
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    addresses?.let {
                        val country_name = it.get(0).countryName
                        if(country_name.uppercase() in listCountries) binding.imageTexaco.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
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
        if(validaCheckBox()){
            startActivity(Intent(this, CreateAccountActivity::class.java).apply {
                putExtra(constants.TYPE_ACCOUNT, type)
                putExtra(constants.ISEDIT, false)
            })
            finish()
        } else {
            showAlertCheckedButton()
        }
    }

    private fun showAlertCheckedButton() {
        val builder = AlertDialog.Builder(this@SelectProfileActivity)
        builder.setTitle("Check necesario")
        builder.setMessage("Para continuar, es necesario que de click en el check para aceptar términos y condiciones, así como aviso de privacidad.")
        builder.setPositiveButton("ENTENDIDO", { dialog, which ->  })
        val dialog = builder.create()
        dialog.show()
    }

    private fun setUrlForDocumentation() {
        val spannableTerms: ClickableSpan = object: ClickableSpan() {
            override fun onClick(p0: View) {
                openBrowser("https://latinamerica.chevronlubricants.com/es_mx/home/privacy-statement/terminos-y-condiciones.html")
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }

        val spannablePrivacy: ClickableSpan = object: ClickableSpan() {
            override fun onClick(p0: View) {
                openBrowser("https://latinamerica.chevronlubricants.com/es_mx/home/privacy-statement.html")
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }

        val terms = SpannableString("Terminos y condiciones")
        terms.setSpan(spannableTerms, 0, terms.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val cs: CharSequence = TextUtils.expandTemplate("*Acepto los ^1", terms)

        val privacy = SpannableString("Aviso de privacidad")
        privacy.setSpan(spannablePrivacy, 0, privacy.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val csp: CharSequence = TextUtils.expandTemplate("*Acepto el ^1", privacy)

        binding.textTerms.text = cs
        binding.textTerms.movementMethod = LinkMovementMethod.getInstance()
        binding.textTerms.highlightColor = Color.TRANSPARENT
        binding.textPrivacy.text = csp
        binding.textPrivacy.movementMethod = LinkMovementMethod.getInstance()
        binding.textPrivacy.highlightColor = Color.TRANSPARENT
    }

    fun openBrowser(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)
    }

    fun validaCheckBox(): Boolean{
        return binding.checkBox.isChecked
    }


}