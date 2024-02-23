package com.trend.feature_tests.data.repository

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import kotlinx.coroutines.flow.Flow

interface TestsRepository {
    suspend fun getTests(request: TestRequest): Flow<TestResponse>
    suspend fun updateStatusLessons(request: StatusLessonsRequest): Flow<StatusLessonsResponse>
}