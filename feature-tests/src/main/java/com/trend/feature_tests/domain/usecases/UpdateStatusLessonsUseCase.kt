package com.trend.feature_tests.domain.usecases

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import kotlinx.coroutines.flow.Flow

interface UpdateStatusLessonsUseCase {
    suspend fun invoke(request: StatusLessonsRequest): Flow<StatusLessonsResponse>
}