package com.trend.feature_accounts.domain.usecases

import com.trend.feature_accounts.data.repositories.CitiesRepository
import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CitiesUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
):  CitiesUseCase{
    override suspend fun invoke(city: CitiesRequest): Flow<CitiesResponse> {
        return citiesRepository.getRemoteCities(city)
    }
}