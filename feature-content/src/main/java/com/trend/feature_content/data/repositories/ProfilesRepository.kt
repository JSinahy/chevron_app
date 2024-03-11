package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.TrendsCompletedResponse
import kotlinx.coroutines.flow.Flow

interface ProfilesRepository {
    suspend fun getBrandsCompleted(request: Int): Flow<TrendsCompletedResponse>
}