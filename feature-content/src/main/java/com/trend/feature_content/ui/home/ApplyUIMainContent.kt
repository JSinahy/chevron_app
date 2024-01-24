package com.trend.feature_content.ui.home

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.trend.chevron.R
import com.trend.chevron.databinding.FragmentHomeBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.TypeImage
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setBlackColor
import com.trend.feature_common.extensiones.setBubbleDelo
import com.trend.feature_common.extensiones.setBubbleHavoline
import com.trend.feature_common.extensiones.setBubbleHavoline4T
import com.trend.feature_common.extensiones.setBubbleTexaco
import com.trend.feature_common.extensiones.setCharacterDelo
import com.trend.feature_common.extensiones.setCharacterHavoline
import com.trend.feature_common.extensiones.setCharacterHavoline4T
import com.trend.feature_common.extensiones.setCharacterTexaco
import com.trend.feature_common.extensiones.setDeloLogo
import com.trend.feature_common.extensiones.setGoinOnButtonDelo
import com.trend.feature_common.extensiones.setGoinOnButtonHavoline
import com.trend.feature_common.extensiones.setGoinOnButtonTexaco
import com.trend.feature_common.extensiones.setHavoline4tLogo
import com.trend.feature_common.extensiones.setHavolineLogo
import com.trend.feature_common.extensiones.setHeaderAndFooter
import com.trend.feature_common.extensiones.setStopBackgroundDelo
import com.trend.feature_common.extensiones.setStopBackgroundHavoline
import com.trend.feature_common.extensiones.setStopBackgroundHavoline4T
import com.trend.feature_common.extensiones.setStopBackgroundTexaco
import com.trend.feature_common.extensiones.setTexacoLogo
import com.trend.feature_common.extensiones.setTruckImage
import com.trend.feature_common.extensiones.setWhiteColor
import com.trend.feature_common.extensiones.setYellowColor
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

}