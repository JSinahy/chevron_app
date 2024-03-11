package com.trend.feature_trends.domain.usecases

import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface GetTrendsCompletedUseCase {
    suspend fun invoke(request: Int): Flow<TrendsCompletedResponse>
}