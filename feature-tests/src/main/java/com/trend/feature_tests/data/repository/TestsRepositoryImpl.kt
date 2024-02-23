package com.trend.feature_tests.data.repository

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import com.trend.feature_tests.data.ds.TestRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TestsRepositoryImpl @Inject constructor(
    private val testRemoteDataSource: TestRemoteDataSource
): TestsRepository {
    override suspend fun getTests(request: TestRequest): Flow<TestResponse> {
        return testRemoteDataSource.getTests(request)
    }

    override suspend fun updateStatusLessons(request: StatusLessonsRequest): Flow<StatusLessonsResponse> {
        return testRemoteDataSource.updateStatusLesson(request)
    }
}