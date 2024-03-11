package com.trend.feature_content.data.ds

import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CitiesRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiServices
): CitiesRemoteDataSource {
    override suspend fun getRemoteCities(city: CitiesRequest) = flow {
        emit(apiService.getCities(city))
    }

}