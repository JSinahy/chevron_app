package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface GetBrandsCompletedUseCase {
    suspend fun invoke(request: Int): Flow<TrendsCompletedResponse>
}