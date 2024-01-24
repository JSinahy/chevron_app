package com.trend.feature_accounts.data.repositories

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import kotlinx.coroutines.flow.Flow

interface ServiceStationsRepository {
    suspend fun getRemoteServiceStations(request: SSRequest): Flow<SSResponse>
}