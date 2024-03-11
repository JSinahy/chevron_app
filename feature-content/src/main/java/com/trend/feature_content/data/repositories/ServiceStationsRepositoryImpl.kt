package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import com.trend.feature_content.data.ds.ServiceStationsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceStationsRepositoryImpl @Inject constructor(
    private val serviceStationsRemoteDataSource: ServiceStationsRemoteDataSource
): ServiceStationsRepository {
    override suspend fun getRemoteServiceStations(request: SSRequest): Flow<SSResponse> {
        return serviceStationsRemoteDataSource.getRemoteServiceStations(request)
    }
}