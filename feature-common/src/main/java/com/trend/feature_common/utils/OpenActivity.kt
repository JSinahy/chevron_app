package com.trend.feature_common.utils

interface OpenActivity {
    fun <T> open(from: T, to: T)
}