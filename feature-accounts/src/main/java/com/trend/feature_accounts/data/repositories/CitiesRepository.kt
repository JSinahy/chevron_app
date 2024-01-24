package com.trend.feature_accounts.data.repositories

import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    suspend fun getRemoteCities(city: CitiesRequest): Flow<CitiesResponse>
}