package com.trend.feature_common.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHelper {
    val TYPE_ACCOUNT = "TYPE_ACCOUNT"
    val ID_ENTERPRISE = "ID_ENTERPRISE"
    val ID_USER = "ID_USER"
    val CELLPHONE = "CELLPHONE"
    val ISLOGGED = "ISLOGGED"
    val NAME_USER = "NAME_USER"
    val LAST_NAME_USER = "LAST_NAME_USER"
    val TIME_TO_STUDY = "TIME_TO_STUDY"
    val BIRTHDAY = "BIRTHDAY"
    val EMAIL = "EMAIL"
    val ID_COUNTRY = "ID_COUNTRY"
    val ID_CITY = "ID_CITY"
    val ID_POS = "ID_POS"
    val ID_SERVICE_STATION = "ID_SERVICE_STATION"
    val SERVICE_STATION = "SERVICE_STATION"
    val COUNTRY_CODE = "COUNTRY_CODE"

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var SharedPreferences.typeAccount
        get() = getString(TYPE_ACCOUNT, "")
        set(value) {
            editMe {
                it.putString(TYPE_ACCOUNT, value)
            }
        }

    var SharedPreferences.isLogged
        get() = getBoolean(ISLOGGED, false)
        set(value) {
            editMe {
                it.putBoolean(ISLOGGED, value)
            }
        }

    var SharedPreferences.countryCode
        get() = getString(COUNTRY_CODE, "")
        set(value) {
            editMe {
                it.putString(COUNTRY_CODE, value)
            }
        }

    var SharedPreferences.idEnterprise
        get() = getInt(ID_ENTERPRISE, 0)
        set(value) {
            editMe {
                it.putInt(ID_ENTERPRISE, value)
            }
        }

    var SharedPreferences.idUser
        get() = getInt(ID_USER, 0)
        set(value) {
            editMe {
                it.putInt(ID_USER, value)
            }
        }

    var SharedPreferences.cellPhone
        get() = getString(CELLPHONE, "")
        set(value) {
            editMe {
                it.putString(CELLPHONE, value)
            }
        }

    var SharedPreferences.nameUser
        get() = getString(NAME_USER, "")
        set(value) {
            editMe {
                it.putString(NAME_USER, value)
            }
        }

    var SharedPreferences.lastNameUser
        get() = getString(LAST_NAME_USER, "")
        set(value) {
            editMe {
                it.putString(LAST_NAME_USER, value)
            }
        }

    var SharedPreferences.timeToStudy
        get() = getInt(TIME_TO_STUDY, 0)
        set(value) {
            editMe {
                it.putInt(TIME_TO_STUDY, value)
            }
        }

    var SharedPreferences.birthday
        get() = getString(BIRTHDAY, "00")
        set(value) {
            editMe {
                it.putString(BIRTHDAY, value)
            }
        }
    var SharedPreferences.email
        get() = getString(EMAIL, "")
        set(value) {
            editMe {
                it.putString(EMAIL, value)
            }
        }

    var SharedPreferences.idCountry
        get() = getInt(ID_COUNTRY, -1)
        set(value) {
            editMe {
                it.putInt(ID_COUNTRY, value)
            }
        }

    var SharedPreferences.idCity
        get() = getInt(ID_CITY, -1)
        set(value) {
            editMe {
                it.putInt(ID_CITY, value)
            }
        }

    var SharedPreferences.idPos
        get() = getString(ID_POS, "-1")
        set(value) {
            editMe {
                it.putString(ID_POS, value)
            }
        }

    var SharedPreferences.idServiceStation
        get() = getInt(ID_SERVICE_STATION, -1)
        set(value) {
            editMe {
                it.putInt(ID_SERVICE_STATION, value)
            }
        }

    var SharedPreferences.serviceStation
        get() = getString(SERVICE_STATION, "")
        set(value) {
            editMe {
                it.putString(SERVICE_STATION, value)
            }
        }


    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }
}