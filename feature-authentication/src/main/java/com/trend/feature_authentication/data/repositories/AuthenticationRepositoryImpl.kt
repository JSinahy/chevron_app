package com.trend.feature_authentication.data.repositories

import com.trend.feature_authentication.data.ds.AuthenticationRemoteDataSource
import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource
): AuthenticationRepository {
    override suspend fun loginWithCellNumber(request: LoginModel): Flow<LoginResponse> {
        return authenticationRemoteDataSource.loginWithCellNumber(request)
    }

    override suspend fun validateOTP(request: CheckOTPModel): Flow<GenericResponse> {
        return authenticationRemoteDataSource.validateOTP(request)
    }

    override suspend fun generateOTP(request: GenerateOTPModel): Flow<GenerateOTPResponse> {
        return authenticationRemoteDataSource.generateOTP(request)
    }
}