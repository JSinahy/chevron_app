package com.trend.feature_content.data.ds

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.UsersModel
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AccountsRemoteDataSource {
    suspend fun createAccount(account: UsersModel): Flow<GenericResponse>
    suspend fun updateAccount(account: UsersModel): Flow<GenericResponse>
    suspend fun deleteAccount(account: DeleteProfile): Flow<GenericResponse>
}