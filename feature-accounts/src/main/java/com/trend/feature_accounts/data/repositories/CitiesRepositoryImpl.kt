package com.trend.feature_accounts.data.repositories

import com.trend.feature_accounts.data.ds.CitiesRemoteDataSource
import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesRemoteDataSource: CitiesRemoteDataSource
): CitiesRepository {
    override suspend fun getRemoteCities(city: CitiesRequest): Flow<CitiesResponse> {
        return citiesRemoteDataSource.getRemoteCities(city)
    }


}