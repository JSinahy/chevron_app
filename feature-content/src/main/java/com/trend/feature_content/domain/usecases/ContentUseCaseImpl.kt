package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_content.data.repositories.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentUseCaseImpl @Inject constructor(
    private val contentRepository: ContentRepository
): ContentUseCase {
    override suspend fun invoke(request: MainContentRequest): Flow<MainContentResponse> {
        return contentRepository.getRoadsAndStops(request)
    }
}