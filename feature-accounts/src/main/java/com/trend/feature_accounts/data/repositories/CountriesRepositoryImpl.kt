package com.trend.feature_accounts.data.repositories

import com.trend.feature_accounts.data.ds.CountriesRemoteDataSource
import com.trend.feature_common.models.CountriesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesRemoteDataSource: CountriesRemoteDataSource
): CountriesRepository {
    override suspend fun getRemoteCountries(): Flow<CountriesResponse> {
        return countriesRemoteDataSource.getRemoteCountries()
    }
}