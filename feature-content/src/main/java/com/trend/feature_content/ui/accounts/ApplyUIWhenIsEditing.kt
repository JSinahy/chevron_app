package com.trend.feature_content.ui.accounts

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.utils.PreferencesHelper.cellPhone
import com.trend.feature_common.utils.PreferencesHelper.email
import com.trend.feature_common.utils.PreferencesHelper.idServiceStation
import com.trend.feature_common.utils.PreferencesHelper.lastNameUser
import com.trend.feature_common.utils.PreferencesHelper.nameUser
import com.trend.feature_common.utils.PreferencesHelper.serviceStation
import com.trend.feature_content.databinding.ActivityCreateAccountBinding

interface ApplyUIWhenIsEditing {
    fun applyUiWhenIdEdit(
        context: Context,
        edit: Boolean,
        binding: ActivityCreateAccountBinding,
        prefs: SharedPreferences,
        typeAccount: TypeAccount
    )
}

class ApplyUIWhenIsEditingImpl: ApplyUIWhenIsEditing {
    override fun applyUiWhenIdEdit(
        context: Context,
        edit: Boolean,
        binding: ActivityCreateAccountBinding,
        prefs: SharedPreferences,
        typeAccount: TypeAccount
    ) {
        if(edit){
            binding.textName.setText(prefs.nameUser)
            binding.textLastname.setText(prefs.lastNameUser)
            binding.textPersonalPhone.setText(prefs.cellPhone)
            binding.textEmail.setText(prefs.email)
            binding.textPOS.setText(when(typeAccount) {
                TypeAccount.HAVOLINE -> prefs.serviceStation
                TypeAccount.TEXACO -> prefs.idServiceStation.toString()
                else -> ""
            })
            binding.imgLogo.visibility = View.GONE
            binding.imgSlogan.visibility = View.GONE
            binding.textHeaderTitle.visibility = View.GONE
            binding.textHeader.visibility = View.GONE

            binding.imgEdit.visibility = View.VISIBLE
            binding.imgProfile.visibility = View.VISIBLE
            binding.textEdit.visibility = View.VISIBLE
            binding.textProfile.visibility = View.VISIBLE

            binding.btnSave.text = "Actualizar"
        }
    }
}