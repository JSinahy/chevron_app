package com.trend.feature_trends.data.ds

import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface TrendsRemoteDataSource {
    suspend fun getCompleted(request: Int): Flow<TrendsCompletedResponse>
}