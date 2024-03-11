package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.CountriesResponse
import com.trend.feature_content.data.repositories.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
): CountriesUseCase {
    override suspend fun invoke(): Flow<CountriesResponse> {
        return countriesRepository.getRemoteCountries()
    }
}