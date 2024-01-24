package com.trend.feature_common.delegates

import android.content.Context
import com.trend.feature_common.extensiones.constants
import com.trend.feature_common.models.LoginData
import com.trend.feature_common.utils.DetectTypeAccount
import com.trend.feature_common.utils.PreferencesHelper.birthday
import com.trend.feature_common.utils.PreferencesHelper.cellPhone
import com.trend.feature_common.utils.PreferencesHelper.countryCode
import com.trend.feature_common.utils.PreferencesHelper.customPreference
import com.trend.feature_common.utils.PreferencesHelper.email
import com.trend.feature_common.utils.PreferencesHelper.idCity
import com.trend.feature_common.utils.PreferencesHelper.idCountry
import com.trend.feature_common.utils.PreferencesHelper.idEnterprise
import com.trend.feature_common.utils.PreferencesHelper.idServiceStation
import com.trend.feature_common.utils.PreferencesHelper.idUser
import com.trend.feature_common.utils.PreferencesHelper.lastNameUser
import com.trend.feature_common.utils.PreferencesHelper.nameUser
import com.trend.feature_common.utils.PreferencesHelper.serviceStation
import com.trend.feature_common.utils.PreferencesHelper.timeToStudy
import com.trend.feature_common.utils.PreferencesHelper.typeAccount

interface ApplySharedPreferences {
    fun applyUpdateSharedPreferences(
        context: Context,
        name: String,
        data: LoginData
    )
}

class ApplySharedPreferencesImpl: ApplySharedPreferences {
    override fun applyUpdateSharedPreferences(context: Context, name: String, data: LoginData) {
        val prefs = customPreference(context, constants.CUSTOM_PREF_NAME)
        prefs.idEnterprise = data.ch_IdEnterprise
        prefs.idUser = data.ch_Id
        prefs.countryCode = data.ch_CountryCode
        prefs.cellPhone = data.ch_Phone
        prefs.typeAccount = DetectTypeAccount.getTypeAccount(data.ch_IdEnterprise).name
        prefs.nameUser = data.ch_Name
        prefs.lastNameUser = data.ch_LastName
        prefs.birthday = data.ch_Birthday
        prefs.email = data.ch_Email
        prefs.idCountry = data.ch_IdCountry
        prefs.idCity = data.ch_IdCity
        prefs.idServiceStation = data.ch_IdServiceStation
        prefs.serviceStation = data.ch_ServiceStation
        prefs.timeToStudy = data.ch_StudyTime
    }

}