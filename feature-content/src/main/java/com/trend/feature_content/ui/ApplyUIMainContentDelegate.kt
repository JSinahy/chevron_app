package com.trend.feature_content.ui

import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.trend.chevron.R
import com.trend.chevron.databinding.ActivityMainContentBinding
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.extensiones.setBackgroundDelo
import com.trend.feature_common.extensiones.setBackgroundHavoline
import com.trend.feature_common.extensiones.setBackgroundHavoline4t
import com.trend.feature_common.extensiones.setBackgroundTexaco
import com.trend.feature_common.extensiones.setDeloButtonBackground
import com.trend.feature_common.extensiones.setHavoline4tButtonBackground
import com.trend.feature_common.extensiones.setTexacoButtonBackground

interface ApplyUIMainContentDelegate {
    fun applyUISideBar(
        binding: ActivityMainContentBinding,
        typeAccount: TypeAccount
    )
}

class ApplyUIMainContentDelegateImpl: ApplyUIMainContentDelegate {
    override fun applyUISideBar(binding: ActivityMainContentBinding, typeAccount: TypeAccount) {
        when(typeAccount) {
            TypeAccount.HAVOLINE -> {
                binding.includeMainContent.moviesAppBar.setBackgroundHavoline(binding.root.context)
            }
            TypeAccount.TEXACO -> {
                binding.includeMainContent.moviesAppBar.setBackgroundTexaco(binding.root.context)
                binding.includeSidebarContent.navigationLayout.setBackgroundTexaco()
                binding.includeMainContent.moviesAppBar.setBackgroundTexaco(binding.root.context)
                binding.includeSidebarContent.buttonCloseSession.setTexacoButtonBackground()
                binding.includeSidebarContent.textExpert.text = "Experto Texaco"
            }
            TypeAccount.HAVOLINE4T -> {
                binding.includeSidebarContent.navigationLayout.setBackgroundHavoline4t()
                binding.includeMainContent.moviesAppBar.setBackgroundHavoline4t(binding.root.context)
                binding.includeSidebarContent.textExpert.text = "Experto Havoline 4T"
                binding.includeSidebarContent.buttonCloseSession.setHavoline4tButtonBackground()
            }
            TypeAccount.DELO -> {
                binding.includeSidebarContent.navigationLayout.setBackgroundDelo()
                binding.includeMainContent.moviesAppBar.setBackgroundDelo(binding.root.context)
                binding.includeSidebarContent.textExpert.text = "Experto DELO"
                binding.includeSidebarContent.buttonCloseSession.setDeloButtonBackground()
            }
        }
    }

}