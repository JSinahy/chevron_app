package com.trend.feature_tests.ui

import android.graphics.Color
import androidx.appcompat.content.res.AppCompatResources
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivityMainExamenBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setCharacterDelo
import com.trend.feature_common.extensiones.setCharacterHavoline4T
import com.trend.feature_common.extensiones.setCharacterTexaco
import com.trend.feature_common.extensiones.setDeloContinueButtonBackground
import com.trend.feature_common.extensiones.setTexacoWhiteButtonBackground
import com.trend.feature_common.utils.DrawableUtils

interface ApplyUITestContent {
    fun applyMainContent(
        typeAccount: TypeAccount,
        binding: ActivityMainExamenBinding
    )
}

class ApplyUITestContentImpl: ApplyUITestContent {
    override fun applyMainContent(typeAccount: TypeAccount, binding: ActivityMainExamenBinding) {
        when(typeAccount){
            TypeAccount.TEXACO -> {
                binding.mainLayout.setBackgroundTexaco()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getDarkRedFinishGlobe()))
                binding.textWelcome.setTextColor(Color.parseColor("#000000"))
                binding.subtitleWelcome.setTextColor(Color.parseColor("#000000"))
                binding.characterImage.setCharacterTexaco()
                binding.nextButton.setTexacoWhiteButtonBackground()
                binding.nextButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayTexaco()), null)
            }
            TypeAccount.DELO -> {
                binding.mainLayout.setBackgroundDelo()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBlueFinishGlobe()))
                binding.textWelcome.setTextColor(Color.parseColor("#FFFFFF"))
                binding.subtitleWelcome.setTextColor(Color.parseColor("#FFFFFF"))
                binding.characterImage.setCharacterDelo()
                binding.nextButton.setDeloContinueButtonBackground()
                binding.nextButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayDelo()), null)
            }
            TypeAccount.HAVOLINE -> {}
            TypeAccount.HAVOLINE4T -> {
                binding.mainLayout.setBackgroundHavoline4t()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getDarkRedFinishGlobe()))
                binding.subtitleWelcome.setTextColor(Color.parseColor("#FFFFFF"))
                binding.characterImage.setCharacterHavoline4T()
                binding.nextButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlay()), null)
            }
        }
    }

}