package com.trend.feature_authentication.domain.usecases

import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import kotlinx.coroutines.flow.Flow

interface GenerateOTPUseCase {
    suspend fun invoke(request: GenerateOTPModel): Flow<GenerateOTPResponse>
}