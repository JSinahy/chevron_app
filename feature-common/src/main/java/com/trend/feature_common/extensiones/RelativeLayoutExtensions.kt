package com.trend.feature_common.extensiones

import android.annotation.SuppressLint
import android.widget.RelativeLayout
import com.trend.chevron.R

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setBackgroundDelo() {
    background = resources.getDrawable(R.drawable.delo_gradient_background, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setBackgroundTexaco() {
    background = resources.getDrawable(R.drawable.texaco_gradient_background, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setStopBackgroundTexaco() {
    background = resources.getDrawable(R.drawable.generic_red_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setGoinOnButtonTexaco() {
    background = resources.getDrawable(R.drawable.generic_red_light_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setStopBackgroundHavoline() {
    background = resources.getDrawable(R.drawable.generic_black_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setStopBackgroundDelo() {
    background = resources.getDrawable(R.drawable.generic_blue_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setStopBackgroundHavoline4T() {
    background = resources.getDrawable(R.drawable.generic_dark_red_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setGoinOnButtonHavoline() {
    background = resources.getDrawable(R.drawable.generic_yellow_button_rounded_corners, null)
}

@SuppressLint("UseCompatLoadingForDrawables")
fun RelativeLayout.setGoinOnButtonDelo() {
    background = resources.getDrawable(R.drawable.generic_blue_light_button_rounded_corners, null)
}

fun RelativeLayout.setDeloButton() {
    background = resources.getDrawable(R.drawable.generic_blue_light_button_rounded_corners, null)
}

fun RelativeLayout.setHavoline4TButton() {
    background = resources.getDrawable(R.drawable.generic_yellow_button_rounded_corners, null)
}