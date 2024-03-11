package com.trend.feature_trends.data.ds

import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendsRemoteDataSourceImpl @Inject constructor(
     private val apiService: ApiServices
): TrendsRemoteDataSource {
    override suspend fun getCompleted(request: Int) = flow {
       emit(apiService.updateCustomerEnterprise(request))
    }
}