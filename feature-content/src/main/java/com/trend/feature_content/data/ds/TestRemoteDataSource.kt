package com.trend.feature_content.data.ds

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import kotlinx.coroutines.flow.Flow

interface TestRemoteDataSource {
    suspend fun getTests(request: TestRequest): Flow<TestResponse>
    suspend fun updateStatusLesson(request: StatusLessonsRequest): Flow<StatusLessonsResponse>
}