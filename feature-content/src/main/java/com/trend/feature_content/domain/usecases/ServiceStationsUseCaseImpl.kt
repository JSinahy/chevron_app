package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import com.trend.feature_content.data.repositories.ServiceStationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServiceStationsUseCaseImpl @Inject constructor(
    private val serviceStationsRepository: ServiceStationsRepository
): ServiceStationsUseCase {
    override suspend fun invoke(request: SSRequest): Flow<SSResponse> {
        return serviceStationsRepository.getRemoteServiceStations(request)
    }
}