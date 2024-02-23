package com.trend.feature_tests.domain.usecases

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_tests.data.repository.TestsRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class UpdateStatusLessonsUseCaseImpl @Inject constructor(
    private val statusLessonsRepository: TestsRepository
): UpdateStatusLessonsUseCase {
    override suspend fun invoke(request: StatusLessonsRequest): Flow<StatusLessonsResponse> {
        return statusLessonsRepository.updateStatusLessons(request)
    }

}