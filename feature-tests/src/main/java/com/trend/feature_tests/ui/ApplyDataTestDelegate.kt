package com.trend.feature_tests.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources

import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.models.CorrectAndIncorrectAnswers
import com.trend.feature_common.models.CorrectIncorrect
import com.trend.feature_common.models.TestModel
import com.trend.feature_common.utils.DrawableUtils
import com.trend.feature_test.databinding.ActivityExamenBinding
import com.trend.feature_tests.ui.ExamenActivity.Companion.corrects

/**
 * Este es para modificar cuando alguien seleccione alguna respuesta
 */
interface ApplyDataTestDelegate {
    fun updateDataUI(
        index: Int,
        data: ArrayList<TestModel>,
        binding: ActivityExamenBinding,
        typeAccount: TypeAccount
    )
}

class ApplyDataTestDelegateImpl: ApplyDataTestDelegate {
    override fun updateDataUI(
        index: Int,
        data: ArrayList<TestModel>,
        binding: ActivityExamenBinding,
        typeAccount: TypeAccount
    ) {
        binding.checkOkA.visibility = View.GONE
        binding.checkOkB.visibility = View.GONE
        binding.checkOkC.visibility = View.GONE
        binding.checkOkD.visibility = View.GONE

        binding.answerAContainer.isEnabled = true
        binding.answerBContainer.isEnabled = true
        binding.answerCContainer.isEnabled = true
        binding.answerDContainer.isEnabled = true

        binding.answerAText.setTextColor(Color.parseColor("#FFFFFF"))
        binding.answerBText.setTextColor(Color.parseColor("#FFFFFF"))
        binding.answerCText.setTextColor(Color.parseColor("#FFFFFF"))
        binding.answerDText.setTextColor(Color.parseColor("#FFFFFF"))

        changeBackgrounQuestion(typeAccount, binding.answerAContainer, binding.root.context)
        changeBackgrounQuestion(typeAccount, binding.answerBContainer, binding.root.context)
        changeBackgrounQuestion(typeAccount, binding.answerCContainer, binding.root.context)
        changeBackgrounQuestion(typeAccount, binding.answerDContainer, binding.root.context)

        CorrectAndIncorrectAnswers.answers = CorrectIncorrect("","", false)
        binding.textQuestion.text = data[index].ch_Question.uppercase()
        val question = data[index]
        binding.answerAContainer.takeIf { question.ch_AnswerA.isNotEmpty() }?.apply {
            visibility = View.VISIBLE
            binding.answerAText.text = question.ch_AnswerA
            setOnClickListener {
                changeBackgrounQuestionSelected(typeAccount, this, binding.root.context)

                changeBackgrounQuestion(typeAccount, binding.answerBContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerCContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerDContainer, binding.root.context)

                if(question.ch_Correct.trim() == "A") {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("A",question.ch_Correct.trim(), true)
                    corrects++
                } else CorrectAndIncorrectAnswers.answers = CorrectIncorrect("A",question.ch_Correct.trim(), false)
            }
        } ?: run {
            binding.answerAContainer.visibility = View.GONE
        }

        binding.answerBContainer.takeIf { question.ch_AnswerB.isNotEmpty() }?.apply {
            visibility = View.VISIBLE
            binding.answerBText.text = question.ch_AnswerB
            setOnClickListener {
                changeBackgrounQuestionSelected(typeAccount, this, binding.root.context)

                changeBackgrounQuestion(typeAccount, binding.answerAContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerCContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerDContainer, binding.root.context)

                if(question.ch_Correct.trim() == "B") {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("B",question.ch_Correct.trim(), true)
                    corrects++
                } else {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("B",question.ch_Correct.trim(), false)
                }
            }
        } ?: run {
            binding.answerBContainer.visibility = View.GONE
        }

        binding.answerCContainer.takeIf { question.ch_AnswerC.isNotEmpty() }?.apply {
            visibility = View.VISIBLE
            binding.answerCText.text = question.ch_AnswerC
            setOnClickListener {
                changeBackgrounQuestionSelected(typeAccount, this, binding.root.context)

                changeBackgrounQuestion(typeAccount, binding.answerBContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerAContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerDContainer, binding.root.context)

                if(question.ch_Correct.trim() == "C") {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("C",question.ch_Correct.trim(), true)
                    corrects++
                } else {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("C",question.ch_Correct.trim(), false)
                }
            }
        } ?: run {
            binding.answerCContainer.visibility = View.GONE
        }

        binding.answerDContainer.takeIf { question.ch_AnswerD.isNotEmpty() }?.apply {
            visibility = View.VISIBLE
            binding.answerDText.text = question.ch_AnswerD
            setOnClickListener {
                changeBackgrounQuestionSelected(typeAccount, this, binding.root.context)

                changeBackgrounQuestion(typeAccount, binding.answerBContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerCContainer, binding.root.context)
                changeBackgrounQuestion(typeAccount, binding.answerAContainer, binding.root.context)

                if(question.ch_Correct.trim() == "D") {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("D",question.ch_Correct.trim(), true)
                    corrects++
                } else {
                    CorrectAndIncorrectAnswers.answers = CorrectIncorrect("D",question.ch_Correct.trim(), false)
                }
            }
        } ?: run {
            binding.answerDContainer.visibility = View.GONE
        }
    }

    private fun changeBackgrounQuestion(typeAccount: TypeAccount, layout: RelativeLayout, context: Context) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavolineRadioButtonNormal())
            }
            TypeAccount.TEXACO -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getTexacoRadioButtonNormal())
            }
            TypeAccount.HAVOLINE4T -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavoline4tRadioButtonNormal())
            }
            TypeAccount.DELO -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getDeloRadioButtonNormal())
            }
            else -> layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavolineRadioButtonNormal())
        }
    }

    private fun changeBackgrounQuestionSelected(typeAccount: TypeAccount, layout: RelativeLayout, context: Context) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavolineRadioButtonSelected())
            }
            TypeAccount.TEXACO -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getTexacoRadioButtonSelected())
            }
            TypeAccount.HAVOLINE4T -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavoline4tRadioButtonSelected())
            }
            TypeAccount.DELO -> {
                layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getDeloRadioButtonSelected())
            }
            else -> layout.background = AppCompatResources.getDrawable(context, DrawableUtils.getHavolineRadioButtonNormal())
        }
    }



}