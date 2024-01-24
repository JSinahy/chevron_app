package com.trend.feature_common.extensiones

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.trend.chevron.R


/** Set bubbles **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBubbleTexaco() {
    setImageDrawable(resources.getDrawable(R.drawable.texaco_img_bubble, null))
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBubbleHavoline() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline_img_bubble, null))
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBubbleDelo() {
    setImageDrawable(resources.getDrawable(R.drawable.delo_img_bubble, null))
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBubbleHavoline4T() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline4t_img_bubble, null))
}

/** Set characters **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setCharacterTexaco() {
    setImageDrawable(resources.getDrawable(R.drawable.texaco_img_character, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setCharacterHavoline() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline_img_character, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setCharacterDelo() {
    setImageDrawable(resources.getDrawable(R.drawable.delo_img_character, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setCharacterHavoline4T() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline4t_img_character, null))
}
/** Set globe finish **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setGlobeTexaco() {
    setImageDrawable(resources.getDrawable(R.drawable.generic_img_globe_finish_white, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setGlobeHavoline4T() {
    setImageDrawable(resources.getDrawable(R.drawable.generic_img_globe_finish_dark_red, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setGlobeDelo() {
    setImageDrawable(resources.getDrawable(R.drawable.generic_img_globe_finish_blue, null))
}
/** Set de background **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBackgroundHavoline() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline_texture_background, null))
    visibility = View.VISIBLE
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBackgroundHavoline4t() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline4t_texture_background, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBackgroundRedTexture() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline4t_texture_background, null))
}

/** Set the header and footer **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setHeaderAndFooter(account: TypeAccount, type: TypeImage) {
    when(account) {
        TypeAccount.HAVOLINE -> {
            when(type) {
                TypeImage.HEADER -> setImageDrawable(resources.getDrawable(R.drawable.havoline_img_header, null))
                TypeImage.FOOTER -> setImageDrawable(resources.getDrawable(R.drawable.havoline_img_footer_account, null))
            }
        }
        TypeAccount.HAVOLINE4T -> {
            when(type) {
                TypeImage.HEADER -> setImageDrawable(resources.getDrawable(R.drawable.havoline4t_img_header, null))
                TypeImage.FOOTER -> setImageDrawable(resources.getDrawable(R.drawable.havoline4t_img_footer, null))
            }
        }
        TypeAccount.DELO -> {
            when(type) {
                TypeImage.HEADER -> setImageDrawable(resources.getDrawable(R.drawable.delo_img_header, null))
                TypeImage.FOOTER -> setImageDrawable(resources.getDrawable(R.drawable.delo_img_footer, null))
            }
        }
        TypeAccount.TEXACO -> {
            when(type) {
                TypeImage.HEADER -> setImageDrawable(resources.getDrawable(R.drawable.texaco_img_footer, null))
                TypeImage.FOOTER -> setImageDrawable(resources.getDrawable(R.drawable.texaco_img_header, null))
            }
        }
    }
}

/** Set brand logos **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setHavolineLogo() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline_img_logo, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setHavoline4tLogo() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline_img_logo, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setDeloLogo() {
    setImageDrawable(resources.getDrawable(R.drawable.delo_img_logo, null))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setTexacoLogo() {
    setImageDrawable(resources.getDrawable(R.drawable.texaco_img_logo, null))
}

/** Set slogans **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setHavoline4tSlogan() {
    setImageDrawable(resources.getDrawable(R.drawable.havoline4t_img_slogan))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setDeloSlogan() {
    setImageDrawable(resources.getDrawable(R.drawable.delo_img_slogan))
}

/** Set thumbs up **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setRedHand() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_thumb_up_red))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setWhiteHand() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_thumb_up_white))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setYellowHand() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_thumb_up_yellow))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setBlueHand() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_thumb_up_blue))
}

/** Set colors **/
fun TextView.setWhiteColor() {
    setTextColor(Color.parseColor("#FFFFFF"))
}
fun TextView.setBlackColor() {
    setTextColor(Color.parseColor("#000000"))
}
fun TextView.setYellowColor() {
    setTextColor(Color.parseColor("#FFD200"))
}

/** Set icons of car and truck **/
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setCarImage() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_car))
}
@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setTruckImage() {
    setImageDrawable(resources.getDrawable(R.drawable.ic_truck))
}


