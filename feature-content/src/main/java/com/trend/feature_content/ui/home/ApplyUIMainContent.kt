package com.trend.feature_content.ui.home

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivityFinishLessonBinding
import com.trend.chevron.databinding.ActivityMainShowContentBinding
import com.trend.chevron.databinding.FragmentHomeBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.TypeImage
import com.trend.feature_common.extensiones.setBackgroundBlue
import com.trend.feature_common.extensiones.setBackgroundDarkRed
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundRed
import com.trend.feature_common.extensiones.setBackgroundRedTexture
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setBlackColor
import com.trend.feature_common.extensiones.setBlueHand
import com.trend.feature_common.extensiones.setBubbleDelo
import com.trend.feature_common.extensiones.setBubbleHavoline
import com.trend.feature_common.extensiones.setBubbleHavoline4T
import com.trend.feature_common.extensiones.setBubbleTexaco
import com.trend.feature_common.extensiones.setCharacterDelo
import com.trend.feature_common.extensiones.setCharacterHavoline
import com.trend.feature_common.extensiones.setCharacterHavoline4T
import com.trend.feature_common.extensiones.setCharacterTexaco
import com.trend.feature_common.extensiones.setDeloButton
import com.trend.feature_common.extensiones.setDeloContinueButtonBackground
import com.trend.feature_common.extensiones.setDeloLogo
import com.trend.feature_common.extensiones.setGlobeDelo
import com.trend.feature_common.extensiones.setGlobeHavoline4T
import com.trend.feature_common.extensiones.setGlobeTexaco
import com.trend.feature_common.extensiones.setGoinOnButtonDelo
import com.trend.feature_common.extensiones.setGoinOnButtonHavoline
import com.trend.feature_common.extensiones.setGoinOnButtonTexaco
import com.trend.feature_common.extensiones.setHavoline4TButton
import com.trend.feature_common.extensiones.setHavoline4tLogo
import com.trend.feature_common.extensiones.setHavolineLogo
import com.trend.feature_common.extensiones.setHeaderAndFooter
import com.trend.feature_common.extensiones.setRedHand
import com.trend.feature_common.extensiones.setStopBackgroundDelo
import com.trend.feature_common.extensiones.setStopBackgroundHavoline
import com.trend.feature_common.extensiones.setStopBackgroundHavoline4T
import com.trend.feature_common.extensiones.setStopBackgroundTexaco
import com.trend.feature_common.extensiones.setTexacoLogo
import com.trend.feature_common.extensiones.setTexacoWhiteButtonBackground
import com.trend.feature_common.extensiones.setTruckImage
import com.trend.feature_common.extensiones.setWhiteColor
import com.trend.feature_common.extensiones.setYellowColor
import com.trend.feature_common.extensiones.setYellowHand
import com.trend.feature_common.models.MainContentModel
import com.trend.feature_common.models.ParadasModel
import com.trend.feature_common.utils.DrawableUtils

interface ApplyUIMainContent {
    fun applyMainContent(
        typeAccount: TypeAccount,
        binding: FragmentHomeBinding
    )

    fun applyUIShieldNumber(
        context: Context,
        route: Int,
        typeAccount: TypeAccount,
        binding: FragmentHomeBinding
    )

    fun applyUIRoutesAdapter(
        context: Context,
        holder: MainRoutesAdapter.ViewHolder,
        typeAccount: TypeAccount,
        mainContentModel: MainContentModel,
        openTest: HomeFragment.OnClickOpenRouteTest
    )

    fun applyUIStopsAdapter(
        context: Context,
        typeAccount: TypeAccount,
        mainStopModel: ParadasModel,
        viewHolder: MainStopsAdapter.ViewHolder
    )

    fun applyUIDisplayContent(
        context: Context,
        typeAccount: TypeAccount,
        binding: ActivityMainShowContentBinding
    )

    fun applyUIFinishLessonContent(
        typeAccount: TypeAccount,
        binding: ActivityFinishLessonBinding
    )
}

class ApplyUIMainContentImpl: ApplyUIMainContent {
    override fun applyMainContent(typeAccount: TypeAccount, binding: FragmentHomeBinding) {
        when(typeAccount){
            TypeAccount.TEXACO -> {
                binding.imgLogo.setTexacoLogo()
                binding.mainLayout.setBackgroundTexaco()
                binding.imgBackground.visibility = View.GONE
                binding.imageCharacter.setCharacterTexaco()
                binding.imageFooter.setHeaderAndFooter(typeAccount, TypeImage.HEADER)
                binding.imgBubble.setBubbleTexaco()
                binding.stopBackground.setStopBackgroundTexaco()
                binding.goinonBackground.setGoinOnButtonTexaco()
                binding.labelTitleStay.setWhiteColor()
                binding.labelGoinon.setWhiteColor()
            }
            TypeAccount.DELO -> {
                binding.imgLogo.setDeloLogo()
                binding.imgBackground.visibility = View.GONE
                binding.imageCharacter.setCharacterDelo()
                binding.imgBubble.setBubbleDelo()
                binding.stopBackground.setStopBackgroundDelo()
                binding.goinonBackground.setGoinOnButtonDelo()
                binding.labelTitleStay.setWhiteColor()
                binding.mainLayout.setBackgroundDelo()
                binding.imgCar.setTruckImage()
                binding.labelGoinon.setWhiteColor()
            }
            TypeAccount.HAVOLINE -> {
                binding.imgLogo.setHavolineLogo()
                binding.imgBackground.setBackgroundHavoline()
                binding.imageCharacter.setCharacterHavoline()
                binding.imgBubble.setBubbleHavoline()
                binding.stopBackground.setStopBackgroundHavoline()
                binding.goinonBackground.setGoinOnButtonHavoline()
                binding.labelTitleStay.setYellowColor()
                binding.labelGoinon.setBlackColor()
            }
            TypeAccount.HAVOLINE4T -> {
                binding.imgLogo.setHavoline4tLogo()
                binding.imgBackground.setBackgroundHavoline4t()
                binding.imageCharacter.setCharacterHavoline4T()
                binding.imgBubble.setBubbleHavoline4T()
                binding.stopBackground.setStopBackgroundHavoline4T()
                binding.goinonBackground.setGoinOnButtonHavoline()
                binding.labelTitleStay.setYellowColor()
                binding.labelGoinon.setBlackColor()
            }
        }
    }

    override fun applyUIShieldNumber(
        context: Context,
        route: Int,
        typeAccount: TypeAccount,
        binding: FragmentHomeBinding
    ) {
        when(typeAccount){
            TypeAccount.HAVOLINE -> {
                when(route) {
                    1 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow1()))
                    2 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow2()))
                    3 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow3()))
                    4 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow4()))
                    5 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow5()))
                }
            }
            TypeAccount.HAVOLINE4T -> {
                when(route) {
                    1 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow1()))
                    2 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow2()))
                    3 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow3()))
                    4 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow4()))
                    5 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow5()))
                }
            }
            TypeAccount.DELO -> {
                when(route) {
                    1 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue1()))
                    2 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue2()))
                    3 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue3()))
                    4 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue4()))
                    5 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue5()))
                }
            }
            TypeAccount.TEXACO -> {
                when(route) {
                    1 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite1()))
                    2 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite2()))
                    3 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite3()))
                    4 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite4()))
                    5 -> binding.shieldYellow.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite5()))
                }
            }
        }
    }

    override fun applyUIRoutesAdapter(
        context: Context,
        holder: MainRoutesAdapter.ViewHolder,
        typeAccount: TypeAccount,
        mainContentModel: MainContentModel,
        openTest: HomeFragment.OnClickOpenRouteTest
    ) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {
                when (mainContentModel.ch_IdRoadmap) {
                    1 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow1()))
                    2 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow2()))
                    3 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow3()))
                    4 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow4()))
                    5 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow5()))
                    6 -> {
                        holder.textRoute.text = mainContentModel.ch_NameRoute.uppercase()
                        holder.imgLock.visibility = View.VISIBLE
                        holder.progressBar.visibility = View.GONE
                        holder.imgRoute.visibility = View.GONE
                        holder.textRoute.setTextColor(Color.parseColor("#808080"))
                        holder.textRoute.setOnClickListener {
                            openTest.onClick(
                                mainContentModel.ch_IdRoadmap,
                                mainContentModel.ch_Paradas[0].ch_Type,
                                mainContentModel.ch_Paradas[0].ch_IdStop
                            )
                        }
                    }
                }
            }
            TypeAccount.TEXACO -> {
                when(mainContentModel.ch_IdRoadmap){
                    1 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite1()))
                    2 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite2()))
                    3 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite3()))
                    4 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite4()))
                    5 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldWhite5()))
                    6 -> {
                        holder.textRoute.text = mainContentModel.ch_NameRoute.uppercase()
                        holder.imgLock.visibility = View.VISIBLE
                        holder.progressBar.visibility = View.GONE
                        holder.imgRoute.visibility = View.GONE
                        holder.textRoute.setTextColor(Color.parseColor("#808080"))
                        holder.textRoute.setOnClickListener {
                            openTest.onClick(mainContentModel.ch_IdRoadmap, mainContentModel.ch_IdTest, mainContentModel.ch_Paradas[0].ch_IdStop)
                        }
                    }
                }
            }
            TypeAccount.DELO -> {
                when(mainContentModel.ch_IdRoadmap){
                    1 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue1()))
                    2 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue2()))
                    3 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue3()))
                    4 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue4()))
                    5 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldBlue5()))
                    6 -> {
                        holder.textRoute.text = mainContentModel.ch_NameRoute.uppercase()
                        holder.imgLock.visibility = View.VISIBLE
                        holder.progressBar.visibility = View.GONE
                        holder.imgRoute.visibility = View.GONE
                        holder.textRoute.setTextColor(Color.parseColor("#808080"))
                        holder.textRoute.setOnClickListener {
                            openTest.onClick(mainContentModel.ch_IdRoadmap, mainContentModel.ch_IdTest, mainContentModel.ch_Paradas[0].ch_IdStop)
                        }
                    }
                }
            }
            TypeAccount.HAVOLINE4T -> {
                when(mainContentModel.ch_IdRoadmap){
                    1 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow1()))
                    2 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow2()))
                    3 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow3()))
                    4 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow4()))
                    5 -> holder.imgRoute.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getShieldYellow5()))
                    6 -> {
                        holder.textRoute.text = mainContentModel.ch_NameRoute.uppercase()
                        holder.imgLock.visibility = View.VISIBLE
                        holder.progressBar.visibility = View.GONE
                        holder.imgRoute.visibility = View.GONE
                        holder.textRoute.setTextColor(Color.parseColor("#808080"))
                        holder.textRoute.setOnClickListener {
                            openTest.onClick(mainContentModel.ch_IdRoadmap, mainContentModel.ch_Paradas[0].ch_Type, mainContentModel.ch_Paradas[0].ch_IdStop)
                        }
                    }
                }
            }
        }
    }

    override fun applyUIStopsAdapter(
        context: Context,
        typeAccount: TypeAccount,
        mainStopModel: ParadasModel,
        viewHolder: MainStopsAdapter.ViewHolder
    ) {
        when(typeAccount){
            TypeAccount.HAVOLINE -> {
                viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                if(mainStopModel.ch_StatusLesson1 == 1) {
                    viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson2 == 1) {
                    viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson3 == 1) {
                    viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
            }
            TypeAccount.TEXACO -> {
                viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                if(mainStopModel.ch_StatusLesson1 == 1) {
                    viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson2 == 1) {
                    viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson3 == 1) {
                    viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
            }

            TypeAccount.DELO -> {

                viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                if(mainStopModel.ch_StatusLesson1 == 1) {
                    viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckDelo()))
                }
                if(mainStopModel.ch_StatusLesson2 == 1) {
                    viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckDelo()))
                }
                if(mainStopModel.ch_StatusLesson3 == 1) {
                    viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckDelo()))
                }
            }
            TypeAccount.HAVOLINE4T -> {
                viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getDisabledPlay()))
                if(mainStopModel.ch_StatusLesson1 == 1) {
                    viewHolder.statusLesson1.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson2 == 1) {
                    viewHolder.statusLesson2.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
                if(mainStopModel.ch_StatusLesson3 == 1) {
                    viewHolder.statusLesson3.setImageDrawable(AppCompatResources.getDrawable(context, DrawableUtils.getCheckHavoline()))
                }
            }
        }
    }

    override fun applyUIDisplayContent(
        context: Context,
        typeAccount: TypeAccount,
        binding: ActivityMainShowContentBinding
    ) {
        when(typeAccount) {
            TypeAccount.TEXACO -> {
                binding.imageCharacter.setCharacterTexaco()
                binding.imgBackgroun.setBackgroundRedTexture()
                binding.toolbarInfo.setBackgroundRed()
            }
            TypeAccount.DELO -> {
                binding.imageCharacter.setCharacterDelo()
                binding.displayMainContainer.setBackgroundDelo()
                binding.imgBackgroun.visibility = View.GONE
                binding.toolbarInfo.setBackgroundBlue()
                binding.endButton.setDeloButton()
            }
            TypeAccount.HAVOLINE4T -> {
                binding.imageCharacter.setCharacterHavoline4T()
                binding.imgBackgroun.setBackgroundRedTexture()
                binding.toolbarInfo.setBackgroundDarkRed()
                binding.endButton.setHavoline4TButton()
            }
            else -> {}
        }
    }

    override fun applyUIFinishLessonContent(
        typeAccount: TypeAccount,
        binding: ActivityFinishLessonBinding
    ) {
        when (typeAccount) {
            TypeAccount.TEXACO -> {
                binding.imgBackgroun.setBackgroundRedTexture()
                binding.imgCharacter.setCharacterTexaco()
                binding.imgGlobe.setGlobeTexaco()
                binding.imgHand.setRedHand()
                binding.textCongrats.setTextColor(Color.parseColor("#000000"))
                binding.textCompleted.setTextColor(Color.parseColor("#000000"))
                binding.returnButton.setTexacoWhiteButtonBackground()
                binding.returnButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayTexaco()), null)
                binding.rvHeader.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBackgroundRed())
            }
            TypeAccount.HAVOLINE4T -> {
                binding.imgBackgroun.setBackgroundHavoline4t()
                binding.imgCharacter.setCharacterHavoline4T()
                binding.imgGlobe.setGlobeHavoline4T()
                binding.imgHand.setYellowHand()
                binding.textCompleted.setTextColor(Color.parseColor("#FFFFFF"))
                binding.returnButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlay()), null)
                binding.rvHeader.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBackgroundDarkRed())
            }
            TypeAccount.DELO -> {
                binding.imgBackgroun.visibility = View.GONE
                binding.generalLayout.setBackgroundDelo()
                binding.imgCharacter.setCharacterDelo()
                binding.imgGlobe.setGlobeDelo()
                binding.imgHand.setBlueHand()
                binding.textCongrats.setTextColor(Color.parseColor("#FFFFFF"))
                binding.textCompleted.setTextColor(Color.parseColor("#FFFFFF"))
                binding.returnButton.setDeloContinueButtonBackground()
                binding.returnButton.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBtnPlayDelo()), null)
                binding.rvHeader.background = AppCompatResources.getDrawable(binding.root.context, DrawableUtils.getBackgroundBlue())
            }
            TypeAccount.HAVOLINE -> {}
        }
    }

}