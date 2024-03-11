package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.CountriesResponse
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun getRemoteCountries(): Flow<CountriesResponse>
}