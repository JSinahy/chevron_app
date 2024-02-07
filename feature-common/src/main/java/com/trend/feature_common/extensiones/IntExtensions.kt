package com.trend.feature_common.extensiones

fun Int.getCardinalNumber(): String {
    return when(this) {
        1 -> "primera"
        2 -> "segunda"
        3 -> "tercera"
        4 -> "cuarta"
        5 -> "quinta"
        6 -> "última"
        else -> "primera"
    }
}