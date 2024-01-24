package com.trend.feature_authentication.domain.usecases

import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginWithCellNumberUseCase {
    suspend fun invoke(request: LoginModel): Flow<LoginResponse>
}