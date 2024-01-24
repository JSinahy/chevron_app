package com.trend.feature_common.utils

import com.trend.feature_common.extensiones.TypeAccount

class DetectTypeAccount {
    companion object {
        fun getTypeAccount(id: Int): TypeAccount {
            return when(id) {
                1 -> TypeAccount.HAVOLINE
                2 -> TypeAccount.HAVOLINE4T
                3 -> TypeAccount.DELO
                4 -> TypeAccount.TEXACO
                else -> TypeAccount.TEXACO
            }
        }

        fun getTypeAccount(valor: String): TypeAccount {
            when(valor) {
                "HAVOLINE" -> return TypeAccount.HAVOLINE
                "HAVOLINE4T" -> return TypeAccount.HAVOLINE4T
                "DELO" -> return TypeAccount.DELO
                "TEXACO" -> return TypeAccount.TEXACO
                else -> return TypeAccount.TEXACO
            }
        }

        fun getNameTypeAccount(type: TypeAccount): String {
            return when(type.ordinal) {
                0 -> "Havoline"
                1 -> "Havoline4T"
                2 -> "Delo"
                3 -> "Texaco"
                else -> "Texaco"
            }
        }
    }
}