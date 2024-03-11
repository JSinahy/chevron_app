package com.trend.feature_content.ui.accounts

import android.widget.TextView
import com.trend.feature_common.extensiones.TypeAccount
import com.trend.feature_common.models.CitiesModel
import com.trend.feature_common.models.CountriesModel
import com.trend.feature_common.models.MonthsModel
import com.trend.feature_common.models.SSModel
import com.trend.feature_common.models.StudyTimeModel
import com.trend.feature_content.databinding.ActivityCreateAccountBinding

interface ApplyValidateForm {
    fun validateForm(
        binding: ActivityCreateAccountBinding,
        typeAccount: TypeAccount
    ): Boolean
}

class ApplyValidateFormImpl: ApplyValidateForm {
    override fun validateForm(
        binding: ActivityCreateAccountBinding,
        typeAccount: TypeAccount
    ): Boolean {
        if(binding.textName.text.isNullOrEmpty()) {
            binding.textName.error = "Campo requerido"
            return false
        } else if (binding.textLastname.text.isNullOrEmpty()) {
            binding.textLastname.error = "Campo requerido"
            return false
        } else if ((binding.spnBirthday.selectedItem as MonthsModel).id.toInt() == -1) {
            (binding.spnBirthday.selectedView as TextView).error = "Campo requerido"
            return false
        } else if((binding.spnCountry.selectedItem as CountriesModel).ch_Id == -1) {
            (binding.spnCountry.selectedView as TextView).error = "Campo requerido"
            return false
        } else if((binding.spnCity.selectedItem as CitiesModel).ch_Id == -1) {
            (binding.spnCity.selectedView as TextView).error = "Campo requerido"
            return false
        } else if(binding.textPersonalPhone.text.isNullOrEmpty()) {
            binding.textPersonalPhone.error = "Campo requerido"
            return false
        } else if(binding.textEmail.text.isNullOrEmpty()) {
            binding.textEmail.error = "Campo requerido"
            return false
        } else if(binding.textPOS.text.isNullOrEmpty() && typeAccount.name == "HAVOLINE"){
            binding.textPOS.error = "Campo requerido"
            return false
        } else if((binding.spnStudyTime.selectedItem as StudyTimeModel).Id == -1) {
            (binding.spnStudyTime.selectedView as TextView).error = "Campo requerido"
            return false
        } else if ((binding.spnSs.selectedItem as SSModel).ch_Id == -1 && typeAccount.name == "TEXACO") {
            (binding.spnStudyTime.selectedView as TextView).error = "Campo requerido"
            return false
        }
        return true
    }

}