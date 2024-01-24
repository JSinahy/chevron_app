package com.trend.feature_accounts.data.ds

import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiServices
): CountriesRemoteDataSource {
    override suspend fun getRemoteCountries() = flow {
        emit(apiService.getCountries())
    }
}