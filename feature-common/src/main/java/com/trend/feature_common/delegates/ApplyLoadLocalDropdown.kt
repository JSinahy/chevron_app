package com.trend.feature_common.delegates

import android.content.Context
import android.widget.Spinner
import com.trend.feature_common.adapters.CountriesCellphoneDataAdapter
import com.trend.feature_common.adapters.MonthsLocalAdapter
import com.trend.feature_common.adapters.StudyTimeAdapter
import com.trend.feature_common.utils.CountriesDataHelper
import com.trend.feature_common.utils.MonthsDataHelper

interface ApplyLoadLocalDropdown {
    fun applyLoadDropDown(
        context: Context,
        spnCountryPhone: Spinner,
        spnBirthday: Spinner,
        spnStudyTime: Spinner,
        countryPhone: CountriesCellphoneDataAdapter
    )

    fun applyLoadCountryPhone(
        context: Context,
        spnCountries: Spinner
    )
}

class ApplyLoadLocalDropdownImpl: ApplyLoadLocalDropdown {
    override fun applyLoadDropDown(
        context: Context,
        spnCountryPhone: Spinner,
        spnBirthday: Spinner,
        spnStudyTime: Spinner,
        countryPhone: CountriesCellphoneDataAdapter
    ) {
        spnCountryPhone.adapter = countryPhone
        spnBirthday.adapter = MonthsLocalAdapter(context, MonthsDataHelper.getMonthsData(), false)
        spnStudyTime.adapter = StudyTimeAdapter(context, MonthsDataHelper.getStudyTime(), false)
    }

    override fun applyLoadCountryPhone(
        context: Context,
        spnCountries: Spinner
    ) {
        spnCountries.adapter = CountriesCellphoneDataAdapter(context, CountriesDataHelper.getCountriesData())
    }

}