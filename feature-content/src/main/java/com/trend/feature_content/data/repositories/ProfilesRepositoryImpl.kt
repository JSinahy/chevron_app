package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.TrendsCompletedResponse
import com.trend.feature_content.data.ds.ProfilesRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfilesRepositoryImpl @Inject constructor(
    private val profilesRemoteDataSource: ProfilesRemoteDataSource
): ProfilesRepository {
    override suspend fun getBrandsCompleted(request: Int): Flow<TrendsCompletedResponse> {
        return profilesRemoteDataSource.getBrandsCompleted(request)
    }
}