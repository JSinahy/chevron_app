package com.trend.feature_accounts.domain.usecases

import com.trend.feature_accounts.data.repositories.CountriesRepository
import com.trend.feature_common.models.CountriesResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
): CountriesUseCase {
    override suspend fun invoke(): Flow<CountriesResponse> {
        return countriesRepository.getRemoteCountries()
    }
}