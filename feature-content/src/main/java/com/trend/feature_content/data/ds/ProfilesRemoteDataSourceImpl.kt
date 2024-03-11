package com.trend.feature_content.data.ds

import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfilesRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
): ProfilesRemoteDataSource {
    override suspend fun getBrandsCompleted(request: Int) = flow  {
        emit(apiServices.updateCustomerEnterprise(request))
    }
}