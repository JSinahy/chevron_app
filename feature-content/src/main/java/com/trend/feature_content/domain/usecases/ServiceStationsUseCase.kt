package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import kotlinx.coroutines.flow.Flow

interface ServiceStationsUseCase {
    suspend fun invoke(request: SSRequest): Flow<SSResponse>
}