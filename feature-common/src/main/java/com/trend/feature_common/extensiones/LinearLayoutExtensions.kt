package com.trend.feature_common.extensiones

import android.annotation.SuppressLint
import android.widget.LinearLayout
import com.trend.chevron.R

@SuppressLint("UseCompatLoadingForDrawables")
fun LinearLayout.setBackgroundTexaco() {
    background = resources.getDrawable(R.drawable.texaco_gradient_background, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun LinearLayout.setBackgroundHavoline() {
    background = resources.getDrawable(R.drawable.texaco_gradient_background, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun LinearLayout.setBackgroundHavoline4t() {
    background = resources.getDrawable(R.drawable.generic_background_dark_red, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun LinearLayout.setBackgroundDelo() {
    background = resources.getDrawable(R.drawable.delo_gradient_background, null)
}