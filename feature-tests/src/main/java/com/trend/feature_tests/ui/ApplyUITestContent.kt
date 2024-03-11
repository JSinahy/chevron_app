package com.trend.feature_tests.ui

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setCharacterDelo
import com.trend.feature_common.extensiones.setCharacterHavoline4T
import com.trend.feature_common.extensiones.setCharacterTexaco
import com.trend.feature_common.extensiones.setDeloButtonBackground
import com.trend.feature_common.extensiones.setDeloContinueButtonBackground
import com.trend.feature_common.extensiones.setHavoline4tButtonBackground
import com.trend.feature_common.extensiones.setHavolineButtonBackground
import com.trend.feature_common.extensiones.setTexacoButtonBackground
import com.trend.feature_common.extensiones.setTexacoWhiteButtonBackground
import com.trend.feature_common.utils.DrawableUtils
import com.trend.feature_test.databinding.ActivityExamenBinding
import com.trend.feature_test.databinding.ActivityExamenResultBinding
import com.trend.feature_test.databinding.ActivityMainExamenBinding
import com.trend.feature_test.databinding.ActivityShareExamenResultBinding
import com.trend.feature_tests.ui.ExamenActivity.Companion.getTypeAssesment

/**
 * Estos delegates estan mas enfocados a cuando uno quiere comprobar una respuesta
 * o para aplicar el UI de la vetana principal de los tests o de los mismos tests
 * y aqui tambien se agrega la venatan final del test.
 */
interface ApplyUITestContent {
    fun applyMainTestContent(
        typeAccount: TypeAccount,
        binding: ActivityMainExamenBinding
    )

    fun applyUITestApplication(
        context: Context,
        binding: ActivityExamenBinding,
        typeAccount: TypeAccount
    )

    fun updateCorrectIncorrectUI(
        context: Context,
        binding: ActivityExamenBinding,
        isCorrect: Boolean,
        response: String
    )

    fun applyUITestResults(
        context: Context,
        binding: ActivityExamenResultBinding,
        califications: Int,
        typeAccount: TypeAccount,
        IdRoute: Int
    )

    fun applyUICertificateResults(
        context: Context,
        binding: ActivityShareExamenResultBinding,
        typeAccount: TypeAccount
    )
}

class ApplyUITestContentImpl: ApplyUITestContent {
    override fun applyMainTestContent(typeAccount: TypeAccount, binding: ActivityMainExamenBinding) {
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

    override fun applyUITestApplication(
        context: Context,
        binding: ActivityExamenBinding,
        typeAccount: TypeAccount
    ) {
        when(typeAccount) {
            TypeAccount.TEXACO -> {
                binding.mainLayout.setBackgroundTexaco()
                binding.answerAContainer.setBackgroundResource(DrawableUtils.getTexacoRadioButtonNormal())
                binding.answerBContainer.setBackgroundResource(DrawableUtils.getTexacoRadioButtonNormal())
                binding.answerCContainer.setBackgroundResource(DrawableUtils.getTexacoRadioButtonNormal())
                binding.answerDContainer.setBackgroundResource(DrawableUtils.getTexacoRadioButtonNormal())
                binding.btnCheck.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getGenericRedButton())
            }
            TypeAccount.HAVOLINE4T -> {
                binding.mainLayout.setBackgroundHavoline4t()
                binding.answerAContainer.setBackgroundResource(DrawableUtils.getHavoline4tRadioButtonNormal())
                binding.answerBContainer.setBackgroundResource(DrawableUtils.getHavoline4tRadioButtonNormal())
                binding.answerCContainer.setBackgroundResource(DrawableUtils.getHavoline4tRadioButtonNormal())
                binding.answerDContainer.setBackgroundResource(DrawableUtils.getHavoline4tRadioButtonNormal())
                binding.btnCheck.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getButtonDarkRedWithYellowBorder())
            }
            TypeAccount.DELO -> {
                binding.mainLayout.setBackgroundDelo()
                binding.answerAContainer.setBackgroundResource(DrawableUtils.getDeloRadioButtonNormal())
                binding.answerBContainer.setBackgroundResource(DrawableUtils.getDeloRadioButtonNormal())
                binding.answerCContainer.setBackgroundResource(DrawableUtils.getDeloRadioButtonNormal())
                binding.answerDContainer.setBackgroundResource(DrawableUtils.getDeloRadioButtonNormal())
                binding.btnCheck.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getGenericDeloButton())
            }
            TypeAccount.HAVOLINE -> {}
        }
    }

    override fun updateCorrectIncorrectUI(
        context: Context,
        binding: ActivityExamenBinding,
        isCorrect: Boolean,
        response: String
    ) {
        when(response) {
            "A" -> {
                binding.answerBContainer.isEnabled = false
                binding.answerCContainer.isEnabled = false
                binding.answerDContainer.isEnabled = false
                binding.answerBText.setTextColor(Color.parseColor("#646464"))
                binding.answerCText.setTextColor(Color.parseColor("#646464"))
                binding.answerDText.setTextColor(Color.parseColor("#646464"))
                if(isCorrect) {
                    binding.checkOkA.visibility = View.VISIBLE
                    binding.checkOkA.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getCheckHavoline()))
                }
                else {
                    binding.checkOkA.visibility = View.VISIBLE
                    binding.checkOkA.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getIncorrect()))
                }
            }
            "B" -> {
                binding.answerAContainer.isEnabled = false
                binding.answerCContainer.isEnabled = false
                binding.answerDContainer.isEnabled = false
                binding.answerAText.setTextColor(Color.parseColor("#646464"))
                binding.answerCText.setTextColor(Color.parseColor("#646464"))
                binding.answerDText.setTextColor(Color.parseColor("#646464"))
                if(isCorrect) {
                    binding.checkOkB.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getCheckHavoline()))
                    binding.checkOkB.visibility = View.VISIBLE
                }
                else {
                    binding.checkOkB.visibility = View.VISIBLE
                    binding.checkOkB.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getIncorrect()))
                }
            }
            "C" -> {
                binding.answerBContainer.isEnabled = false
                binding.answerAContainer.isEnabled = false
                binding.answerDContainer.isEnabled = false
                binding.answerBText.setTextColor(Color.parseColor("#646464"))
                binding.answerAText.setTextColor(Color.parseColor("#646464"))
                binding.answerDText.setTextColor(Color.parseColor("#646464"))
                if(isCorrect) {
                    binding.checkOkC.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getCheckHavoline()))
                    binding.checkOkC.visibility = View.VISIBLE
                }
                else {
                    binding.checkOkC.visibility = View.VISIBLE
                    binding.checkOkC.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getIncorrect()))
                }
            }
            "D" -> {
                binding.answerBContainer.isEnabled = false
                binding.answerCContainer.isEnabled = false
                binding.answerAContainer.isEnabled = false
                binding.answerBText.setTextColor(Color.parseColor("#646464"))
                binding.answerCText.setTextColor(Color.parseColor("#646464"))
                binding.answerAText.setTextColor(Color.parseColor("#646464"))
                if(isCorrect) {
                    binding.checkOkD.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getCheckHavoline()))
                    binding.checkOkD.visibility = View.VISIBLE
                }
                else {
                    binding.checkOkD.visibility = View.VISIBLE
                    binding.checkOkD.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getIncorrect()))
                }
            }
            else -> {

            }
        }
    }

    override fun applyUITestResults(
        context: Context,
        binding: ActivityExamenResultBinding,
        califications: Int,
        typeAccount: TypeAccount,
        IdRoute: Int
    ) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {
                binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpYellow()))
                binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getSadYellow()))
            }
            TypeAccount.TEXACO -> {
                binding.mainLayout.setBackgroundTexaco()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getWhiteFinishGlobe()))
                binding.textMessage.setTextColor(Color.parseColor("#000000"))
                binding.textDetail.setTextColor(Color.parseColor("#000000"))
                binding.characterImage.setCharacterTexaco()
                binding.nextButton.setTexacoWhiteButtonBackground()
                binding.nextButton.setCompoundDrawables(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayTexaco()), null)
            }
            TypeAccount.HAVOLINE4T -> {
                binding.mainLayout.setBackgroundHavoline4t()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getDarkRedFinishGlobe()))
                binding.textMessage.setTextColor(Color.parseColor("#FFFFFF"))
                binding.textDetail.setTextColor(Color.parseColor("#FFFFFF"))
                binding.characterImage.setCharacterHavoline4T()
                binding.nextButton.setCompoundDrawables(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlay()), null)
            }
            TypeAccount.DELO -> {
                binding.mainLayout.setBackgroundDelo()
                binding.imgGlobe.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBlueFinishGlobe()))
                binding.textMessage.setTextColor(Color.parseColor("#FFFFFF"))
                binding.textDetail.setTextColor(Color.parseColor("#FFFFFF"))
                binding.characterImage.setCharacterDelo()
                binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpBlue()))
                binding.nextButton.setDeloContinueButtonBackground()
                binding.nextButton.setCompoundDrawables(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayDelo()), null)
            }
            else -> ""
        }
        val limit = getTypeAssesment(IdRoute)
        if(califications >= limit) {
            when(typeAccount) {
                TypeAccount.TEXACO -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpRed()))
                TypeAccount.HAVOLINE -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpYellow()))
                TypeAccount.HAVOLINE4T -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpYellow()))
                else -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getThumbUpYellow()))
            }

            binding.textMessage.text = "¡Felicidades\nhas desbloqueado la\nsiguiente parada\nestas cerca de\nconvertirte en todo un\nExperto!"
            binding.textDetail.visibility = View.GONE
        } else {
            when(typeAccount) {
                TypeAccount.TEXACO -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getSadGray()))
                TypeAccount.HAVOLINE -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getSadYellow()))
                TypeAccount.HAVOLINE4T -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getSadYellow()))
                else -> binding.imageResult.setImageDrawable(AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getSadYellow()))
            }
        }
        binding.textMessage.text = "¡Continúa aprendiendo\npara convertirte\nen un experto!"
        binding.textDetail.text = "Te recomendamos revisar tus\nlecciones para desbloquear\nla siguiente parada."
        binding.textDetail.visibility = View.VISIBLE
    }

    override fun applyUICertificateResults(
        context: Context,
        binding: ActivityShareExamenResultBinding,
        typeAccount: TypeAccount
    ) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {}
            TypeAccount.HAVOLINE4T -> {
                binding.mainLayout.setBackgroundHavoline4t()
            }
            TypeAccount.DELO -> {
                binding.mainLayout.setBackgroundDelo()
                binding.shareWithFacebook.setDeloButtonBackground()
                binding.saveCertificate.setDeloButtonBackground()
                binding.keepLearning.setDeloButtonBackground()
            }
            TypeAccount.TEXACO -> {
                binding.mainLayout.setBackgroundTexaco()
            }
        }
    }
}

