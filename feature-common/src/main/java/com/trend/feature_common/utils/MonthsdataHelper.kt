package com.trend.feature_common.utils

import com.trend.feature_common.models.MonthsModel
import com.trend.feature_common.models.StudyTimeModel

class MonthsDataHelper {
    companion object {
        var months: ArrayList<MonthsModel> = arrayListOf()
        var study: ArrayList<StudyTimeModel> = arrayListOf()
        fun getMonthsData(): ArrayList<MonthsModel> {
            months.clear()
            months.add(0, MonthsModel("-1", "-- Selecciona el mes de tu cumpleaños --"))
            months.add(MonthsModel("01", "Enero"))
            months.add(MonthsModel("02", "Febrero"))
            months.add(MonthsModel("03", "Marzo"))
            months.add(MonthsModel("04", "Abril"))
            months.add(MonthsModel("05", "Mayo"))
            months.add(MonthsModel("06", "Junio"))
            months.add(MonthsModel("07", "Julio"))
            months.add(MonthsModel("08", "Agosto"))
            months.add(MonthsModel("09", "Septiembre"))
            months.add(MonthsModel("10", "Octubre"))
            months.add(MonthsModel("11", "Noviembre"))
            months.add(MonthsModel("12", "Diciembre"))

            return months
        }

        fun getStudyTime(): ArrayList<StudyTimeModel> {
            study.clear()
            study.add(StudyTimeModel(5, "5 min. de estudio"))
            study.add(StudyTimeModel(10, "10 min. de estudio"))
            study.add(StudyTimeModel(15, "15 min. de estudio"))

            return study
        }
    }
}