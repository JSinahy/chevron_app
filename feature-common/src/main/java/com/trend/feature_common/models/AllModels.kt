package com.trend.feature_common.models

open class GenericResponse{
    var status: Int = 0
    var message: String = ""
}
data class UsersModel (
    val ch_Name: String,
    val ch_LastName: String,
    val ch_Birthday: String,
    val ch_IdCountry : Int,
    val ch_IdCity: Int,
    val ch_CountryCode: String,
    val ch_Cellphone: String,
    val ch_Email: String,
    val ch_IdPos: Int,
    val ch_IdEnterprise: Int,
    val ch_IdServiceStation: Int,
    val ch_ServiceStation: String,
    val ch_StudyTime: Int,
    val ch_IdUser: Int,
    val ch_CreatedAt: String
)
data class DeleteProfile(
    val id_user: Int = 0,
    val id_enterprise: Int = 0
)

data class LoginModel(var cellphone: String, var code: String)
data class LoginResponse(var data: LoginData, var otp: String): GenericResponse()
open class LoginData (
    var ch_Id: Int,
    var ch_Name: String,
    var ch_LastName: String,
    var ch_Email: String,
    var ch_IdEnterprise: Int,
    var ch_CountryCode: String,
    var ch_Phone: String,
    var ch_StudyTime: Int,
    var ch_IdCountry: Int,
    var ch_IdCity: Int,
    var ch_IdPos: Int,
    var ch_IdServiceStation: Int,
    var ch_ServiceStation: String,
    var ch_Birthday: String
)
data class CheckOTPModel(
    var cellphone: String,
    var otp: String
)
data class GenerateOTPResponse(var otp: String): GenericResponse()
data class GenerateOTPModel(var ch_Cellphone: String, var token: String)

data class MainContentRequest(
    var iduser: Int,
    var identerprise: Int
)

data class MainContentResponse(var routes: ArrayList<MainContentModel>): GenericResponse()
data class MainContentModel(
    var ch_IdRoadmap: Int,
    var ch_NameRoute: String,
    var ch_Paradas: ArrayList<ParadasModel>,
    var ch_IdTest: Int
)
data class ParadasModel(
    var ch_IdStop: Int,
    var ch_Type: Int,
    var ch_NameStop: String,
    var ch_SubtitleStop: String,
    var ch_Title1: String,
    var ch_Text1: String,
    var ch_StatusLesson1: Int,
    var ch_Title2: String,
    var ch_Text2: String,
    var ch_StatusLesson2: Int,
    var ch_Title3: String,
    var ch_AudioPath: String,
    var ch_AudioImage: String,
    var ch_StatusLesson3: Int,
    var ch_IdStatus: String
)


data class CountriesModel(var ch_Id: Int, var ch_Name: String, var ch_Flag: String)
data class CountriesResponse(var countries: ArrayList<CountriesModel>): GenericResponse()

data class CitiesModel(var ch_Id: Int, var ch_IdCountry: Int, var ch_Name: String)
data class CitiesResponse(var cities: ArrayList<CitiesModel>): GenericResponse()

data class SSModel(var ch_Id: Int, var ch_ServiceStationName: String)
data class SSResponse(var sss: ArrayList<SSModel>): GenericResponse()

data class CitiesRequest(var ch_IdCountry: Int)
data class SSRequest(var IdCountry: Int)

data class StatusLessonsRequest(var IdUser: Int, var IdStop: Int, var IdLesson: Int)
data class StatusLessonsResponse(var result: String): GenericResponse()


data class TestModel (
    var ch_Id: Int = 0,
    var ch_IdTest: Int = 0,
    var ch_Question:String = "",
    var ch_AnswerA: String = "",
    var ch_AnswerB: String = "",
    var ch_AnswerC: String = "",
    var ch_AnswerD: String = "",
    var ch_Correct: String = ""
)

data class TestRequest(var idTest: Int)

class TestResponse : GenericResponse() {
    val test = ArrayList<TestModel>()
}

data class CountriesCellphoneDataModel(var code: String, var flag: String, val name: String)
data class MonthsModel(var id: String, var name: String)
data class StudyTimeModel(var Id: Int, var Name: String)

data class StopContinueModel(var ruta: String, var parada: String)