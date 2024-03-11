package com.trend.feature_common.extensiones

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.openActivity(destiny: Class<*>, close: Boolean = false, args: Bundle? = null) {
    startActivity(Intent(this, destiny).apply {
        args
    })
    if(close) this.finish()
}

