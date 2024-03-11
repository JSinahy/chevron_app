package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import kotlinx.coroutines.flow.Flow

interface CitiesUseCase {
    suspend fun invoke(city: CitiesRequest): Flow<CitiesResponse>
}