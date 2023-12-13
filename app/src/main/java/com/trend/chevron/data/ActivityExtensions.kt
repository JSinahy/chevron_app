package com.trend.chevron.data

import android.app.Activity
import android.content.Intent

fun Activity.openActivity(destiny: Class<*>) {
    startActivity(Intent(this, destiny).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}