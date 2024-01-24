package com.trend.feature_accounts.data.ds

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServiceStationsRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
): ServiceStationsRemoteDataSource {
    override suspend fun getRemoteServiceStations(request: SSRequest) = flow {
        emit(apiServices.getServiceStations(request))
    }
}