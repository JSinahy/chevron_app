package com.trend.feature_authentication.data.repositories

import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun loginWithCellNumber(request: LoginModel): Flow<LoginResponse>
    suspend fun validateOTP(request: CheckOTPModel): Flow<GenericResponse>
    suspend fun generateOTP(request: GenerateOTPModel): Flow<GenerateOTPResponse>
}