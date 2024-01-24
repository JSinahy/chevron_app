package com.trend.feature_accounts.data.ds

import com.trend.feature_common.models.CountriesResponse
import kotlinx.coroutines.flow.Flow

interface CountriesRemoteDataSource {
    suspend fun getRemoteCountries(): Flow<CountriesResponse>
}