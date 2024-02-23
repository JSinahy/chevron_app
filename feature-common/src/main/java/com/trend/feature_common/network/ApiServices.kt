package com.trend.feature_common.network

import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import com.trend.feature_common.models.CountriesResponse
import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import com.trend.feature_common.models.UsersModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiServices {
    @POST("users")
    suspend fun saveUser(@Body user: UsersModel) : GenericResponse

    @PATCH("users")
    suspend fun updateUser(@Body user: UsersModel) : GenericResponse

    @DELETE("users")
    suspend fun deleteUser(@Body user: DeleteProfile) : GenericResponse

    @Deprecated("This method is deprecated please use [deleteUser]")
    @PATCH("users/delete")
    suspend fun deleteProfile(@Body user: DeleteProfile) : GenericResponse

    @POST("users/login")
    suspend fun login(@Body cellphone: LoginModel): LoginResponse

    @POST("users/checkotp")
    suspend fun checkOTP(@Body checkOtp: CheckOTPModel): GenericResponse

    @POST("users/generateotp")
    suspend fun generateOTP(@Body checkOtp: GenerateOTPModel): GenerateOTPResponse

    @POST("lessons/")
    suspend fun getMainContentByUser(@Body request: MainContentRequest): MainContentResponse

    @GET("general/countries")
    suspend fun getCountries(): CountriesResponse

    @POST("general/cities")
    suspend fun getCities(@Body request: CitiesRequest): CitiesResponse

    @POST("general/ss")
    suspend fun getServiceStations(@Body request: SSRequest): SSResponse

    @PATCH("lessons/")
    suspend fun updateStatusLessons(@Body request: StatusLessonsRequest): StatusLessonsResponse

    @POST("lessons/test")
    suspend fun getTest(@Body request: TestRequest): TestResponse
}