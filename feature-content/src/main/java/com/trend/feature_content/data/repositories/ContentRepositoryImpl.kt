package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_content.data.ds.ContentRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentRemoteDataSource
): ContentRepository {
    override suspend fun getRoadsAndStops(request: MainContentRequest): Flow<MainContentResponse> {
        return contentDataSource.getRoadsAndStops(request)
    }
}