package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import com.trend.feature_content.data.repositories.CitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
):  CitiesUseCase{
    override suspend fun invoke(city: CitiesRequest): Flow<CitiesResponse> {
        return citiesRepository.getRemoteCities(city)
    }
}