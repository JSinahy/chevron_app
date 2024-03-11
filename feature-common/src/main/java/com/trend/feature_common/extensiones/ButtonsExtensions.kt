package com.trend.feature_common.extensiones

import android.annotation.SuppressLint
import android.widget.Button
import com.trend.feature_common.R

/** Set background buttons **/
@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setTexacoButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_red_button, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setTexacoWhiteButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_white_button_border_red, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setDeloButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_blue_button, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setDeloContinueButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_white_button, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setHavoline4tButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_red_button_border_yellow, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun Button.setHavolineButtonBackground() {
    background = resources.getDrawable(R.drawable.generic_black_button_border_yellow, null)
}