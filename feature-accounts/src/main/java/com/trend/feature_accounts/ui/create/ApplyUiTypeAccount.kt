package com.trend.feature_accounts.ui.create

import android.graphics.Color
import android.view.View
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivityCreateAccountBinding
import com.trend.chevron.databinding.ActivitySuccessCreateAccountBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.TypeImage
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setDeloButtonBackground
import com.trend.feature_common.extensiones.setDeloLogo
import com.trend.feature_common.extensiones.setDeloSlogan
import com.trend.feature_common.extensiones.setHavoline4tButtonBackground
import com.trend.feature_common.extensiones.setHavoline4tLogo
import com.trend.feature_common.extensiones.setHavoline4tSlogan
import com.trend.feature_common.extensiones.setHavolineButtonBackground
import com.trend.feature_common.extensiones.setHavolineLogo
import com.trend.feature_common.extensiones.setHeaderAndFooter
import com.trend.feature_common.extensiones.setTexacoButtonBackground
import com.trend.feature_common.extensiones.setTexacoLogo
import com.trend.feature_common.extensiones.setWhiteHand
import com.trend.feature_common.extensiones.setYellowHand

interface ApplyUiTypeAccount {
    fun applyUITypeAccountSuccess(
        binding: ActivitySuccessCreateAccountBinding,
        typeAccount: TypeAccount
    )
    fun applyUiTypeAccount(
        binding: ActivityCreateAccountBinding,
        typeAccount: TypeAccount
    )
}

class ApplyUiTypeAccountImpl: ApplyUiTypeAccount {
    override fun applyUITypeAccountSuccess(
        binding: ActivitySuccessCreateAccountBinding,
        typeAccount: TypeAccount
    ) {
        when(typeAccount){
            TypeAccount.HAVOLINE -> {
                binding.havolineLogo.setHavolineLogo()
                binding.textSuccesss.setTextColor(Color.parseColor("#FFD200"))
                binding.background.setBackgroundHavoline()
                binding.havolineLogo.setHavolineLogo()
                binding.imageHeader.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.imageFooter.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnNext.setHavolineButtonBackground()
                binding.imgHand.setYellowHand();
            }
            TypeAccount.HAVOLINE4T -> {
                binding.havolineLogo.setHavoline4tLogo()
                binding.imageSlogan.setHavoline4tSlogan()
                binding.textSuccesss.setTextColor(Color.parseColor("#FFD200"))
                binding.background.setBackgroundHavoline4t()
                binding.imageHeader.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.imageFooter.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnNext.setHavoline4tButtonBackground()
                binding.imgHand.setYellowHand();
            }
            TypeAccount.DELO -> {
                binding.havolineLogo.setDeloLogo()
                binding.imageSlogan.setDeloSlogan()
                binding.textSuccesss.setTextColor(Color.parseColor("#00D4FF"))
                binding.background.visibility = View.GONE
                binding.mainContent.setBackgroundDelo()
                binding.imageHeader.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.imageFooter.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnNext.setDeloButtonBackground()
                binding.imgHand.setWhiteHand();
            }
            TypeAccount.TEXACO -> {
                binding.havolineLogo.setTexacoLogo()
                binding.imageSlogan.visibility = View.INVISIBLE
                binding.textSuccesss.setTextColor(Color.parseColor("#FFFFFF"))
                binding.background.visibility = View.GONE
                binding.mainContent.setBackgroundTexaco()
                binding.imageHeader.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.imageFooter.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnNext.setTexacoButtonBackground()
                binding.imgHand.setWhiteHand();
            }
        }
    }

    override fun applyUiTypeAccount(
        binding: ActivityCreateAccountBinding,
        typeAccount: TypeAccount
    ) {
        when (typeAccount) {
            TypeAccount.HAVOLINE -> {
                binding.textPOS.visibility = View.VISIBLE
                binding.layoutSS.visibility = View.GONE
                binding.imgLogo.setHavolineLogo()
                binding.background.setBackgroundHavoline()
                binding.accountHeaderH.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.accountFooterH.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnSave.setHavolineButtonBackground()
                binding.btnDelete.setHavolineButtonBackground()
            }
            TypeAccount.HAVOLINE4T -> {
                binding.imgSlogan.setHavoline4tSlogan()
                binding.imgLogo.setHavoline4tLogo()
                binding.background.setBackgroundHavoline4t()
                binding.accountHeaderH.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.accountFooterH.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnSave.setHavoline4tButtonBackground()
                binding.btnDelete.setHavoline4tButtonBackground()
            }
            TypeAccount.DELO -> {
                binding.imgSlogan.setDeloSlogan()
                binding.imgLogo.setDeloLogo()
                binding.mainContent.setBackgroundDelo()
                binding.background.visibility = View.GONE
                binding.accountHeaderH.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.accountFooterH.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnSave.setDeloButtonBackground()
                binding.btnDelete.setDeloButtonBackground()
            }
            TypeAccount.TEXACO -> {
                binding.textPOS.visibility = View.GONE
                binding.layoutSS.visibility = View.VISIBLE
                binding.imgSlogan.visibility = View.INVISIBLE
                binding.imgLogo.setTexacoLogo()
                binding.background.visibility = View.GONE
                binding.mainContent.setBackgroundTexaco()
                binding.accountHeaderH.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.accountFooterH.setHeaderAndFooter(typeAccount, TypeImage.FOOTER)
                binding.btnSave.setTexacoButtonBackground()
                binding.btnSave.setTexacoButtonBackground()
            }
        }
    }

}