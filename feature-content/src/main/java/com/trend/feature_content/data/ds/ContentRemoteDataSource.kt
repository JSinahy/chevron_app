package com.trend.feature_content.data.ds

import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import kotlinx.coroutines.flow.Flow

interface ContentRemoteDataSource {
    suspend fun getRoadsAndStops(request: MainContentRequest): Flow<MainContentResponse>
    suspend fun updateStatusLesson(request: StatusLessonsRequest): Flow<StatusLessonsResponse>
}