package com.trend.feature_common.utils

object EnterprisesUtils {
    fun getName(id: Int): String {
        return when(id) {
            1 -> "havoline"
            2-> "havoline4t"
            3-> "delo"
            4-> "texaco"
            else -> ""
        }
    }
}