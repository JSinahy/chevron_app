package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_content.data.repositories.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateLessonUseCaseImpl @Inject constructor(
    private val contentRepository: ContentRepository
): UpdateLessonUseCase {
    override suspend fun invoke(request: StatusLessonsRequest): Flow<StatusLessonsResponse> {
        return contentRepository.updateStatusLesson(request)
    }
}