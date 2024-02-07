package com.trend.feature_content.data.ds

import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
): ContentRemoteDataSource {
    override suspend fun getRoadsAndStops(request: MainContentRequest) = flow {
        emit(apiServices.getMainContentByUser(request))
    }

    override suspend fun updateStatusLesson(request: StatusLessonsRequest) = flow {
        emit(apiServices.updateStatusLessons(request))
    }
}