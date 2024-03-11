package com.trend.feature_trends.data.repositories

import com.trend.feature_common.models.TrendsCompletedResponse
import com.trend.feature_trends.data.ds.TrendsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendsRepositoryImpl @Inject constructor(
    private val trendsRemoteDataSource: TrendsRemoteDataSource
): TrendsRepository {
    override suspend fun getCompleted(request: Int): Flow<TrendsCompletedResponse> {
        return trendsRemoteDataSource.getCompleted(request)
    }
}