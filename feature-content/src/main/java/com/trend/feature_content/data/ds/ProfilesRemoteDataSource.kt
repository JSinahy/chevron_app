package com.trend.feature_content.data.ds

import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface ProfilesRemoteDataSource {
    suspend fun getBrandsCompleted(request: Int): Flow<TrendsCompletedResponse>
}