package com.trend.feature_content.data.ds

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import kotlinx.coroutines.flow.Flow

interface ServiceStationsRemoteDataSource {
    suspend fun getRemoteServiceStations(request: SSRequest): Flow<SSResponse>
}