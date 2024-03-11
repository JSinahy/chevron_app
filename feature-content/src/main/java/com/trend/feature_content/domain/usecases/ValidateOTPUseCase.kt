package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenericResponse
import kotlinx.coroutines.flow.Flow

interface ValidateOTPUseCase {
    suspend fun invoke(request: CheckOTPModel): Flow<GenericResponse>
}