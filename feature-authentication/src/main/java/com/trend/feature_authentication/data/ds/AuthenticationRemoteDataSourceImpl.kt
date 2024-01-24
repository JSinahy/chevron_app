package com.trend.feature_authentication.data.ds

import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
): AuthenticationRemoteDataSource {
    override suspend fun loginWithCellNumber(request: LoginModel) = flow {
        emit(apiServices.login(request))
    }

    override suspend fun validateOTP(request: CheckOTPModel) = flow {
        emit(apiServices.checkOTP(request))
    }

    override suspend fun generateOTP(request: GenerateOTPModel) = flow {
        emit(apiServices.generateOTP(request))
    }
}