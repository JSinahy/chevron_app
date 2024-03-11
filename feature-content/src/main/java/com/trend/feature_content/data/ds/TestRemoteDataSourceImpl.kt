package com.trend.feature_content.data.ds

import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
) : TestRemoteDataSource {
    override suspend fun getTests(request: TestRequest) = flow {
        emit(apiServices.getTest(request))
    }

    override suspend fun updateStatusLesson(request: StatusLessonsRequest) = flow {
        emit(apiServices.updateStatusLessons(request))
    }
}