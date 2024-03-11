package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.TrendsCompletedResponse
import com.trend.feature_content.data.repositories.ProfilesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBrandsCompletedUseCaseImpl @Inject constructor(
    private val profilesRepository: ProfilesRepository
): GetBrandsCompletedUseCase {
    override suspend fun invoke(request: Int): Flow<TrendsCompletedResponse> {
        return profilesRepository.getBrandsCompleted(request)
    }
}