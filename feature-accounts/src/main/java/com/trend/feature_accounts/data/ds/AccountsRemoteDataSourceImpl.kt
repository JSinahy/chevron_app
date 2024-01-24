package com.trend.feature_accounts.data.ds

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.UsersModel
import com.trend.feature_common.network.ApiServices
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountsRemoteDataSourceImpl @Inject constructor(
    private val apiServices: ApiServices
): AccountsRemoteDataSource {
    override suspend fun createAccount(account: UsersModel) = flow {
        emit(apiServices.saveUser(account))
    }

    override suspend fun updateAccount(account: UsersModel) = flow {
        emit(apiServices.updateUser(account))
    }

    override suspend fun deleteAccount(account: DeleteProfile)= flow {
        emit(apiServices.deleteUser(account))
    }
}