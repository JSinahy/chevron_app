package com.trend.feature_accounts.domain.usecases

import com.trend.feature_common.models.CountriesResponse
import kotlinx.coroutines.flow.Flow

interface CountriesUseCase {
    suspend fun invoke(): Flow<CountriesResponse>
}