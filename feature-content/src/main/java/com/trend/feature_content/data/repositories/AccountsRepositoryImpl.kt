package com.trend.feature_content.data.repositories

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.UsersModel
import com.trend.feature_content.data.ds.AccountsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountsRepositoryImpl @Inject constructor(
    private val accountsRemoteDataSource: AccountsRemoteDataSource
): AccountsRepository {
    override suspend fun createAccount(account: UsersModel): Flow<GenericResponse> {
        return accountsRemoteDataSource.createAccount(account)
    }

    override suspend fun updateAccount(account: UsersModel): Flow<GenericResponse> {
        return accountsRemoteDataSource.updateAccount(account)
    }

    override suspend fun deleteAccount(account: DeleteProfile): Flow<GenericResponse> {
        return accountsRemoteDataSource.deleteAccount(account)
    }
}