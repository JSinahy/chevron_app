package com.trend.feature_trends.domain.usecases

import com.trend.feature_common.models.TrendsCompletedResponse
import com.trend.feature_trends.data.repositories.TrendsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendsCompletedUseCaseImpl @Inject constructor(
    private val trendsRepository: TrendsRepository
): GetTrendsCompletedUseCase {
    override suspend fun invoke(request: Int): Flow<TrendsCompletedResponse> {
        return trendsRepository.getCompleted(request)
    }
}