package com.trend.feature_trends.data.repositories

import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface TrendsRepository {
    suspend fun getCompleted(request: Int): Flow<TrendsCompletedResponse>
}