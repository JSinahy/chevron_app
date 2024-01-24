package com.trend.feature_accounts.data.repositories

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.UsersModel
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {
    suspend fun createAccount(account: UsersModel): Flow<GenericResponse>
    suspend fun updateAccount(account: UsersModel): Flow<GenericResponse>
    suspend fun deleteAccount(account: DeleteProfile): Flow<GenericResponse>
}