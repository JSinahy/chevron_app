package com.trend.feature_common.extensiones

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.appbar.AppBarLayout
import com.trend.chevron.R

fun AppBarLayout.setBackgroundHavoline(context: Context) {
    background = AppCompatResources.getDrawable(context, R.drawable.generic_background_black)
}

fun AppBarLayout.setBackgroundHavoline4t(context: Context) {
    background = AppCompatResources.getDrawable(context, R.drawable.generic_background_dark_red)
}

fun AppBarLayout.setBackgroundTexaco(context: Context) {
    background = AppCompatResources.getDrawable(context, R.drawable.generic_background_red)
}

fun AppBarLayout.setBackgroundDelo(context: Context) {
    background = AppCompatResources.getDrawable(context, R.drawable.generic_background_blue)
}