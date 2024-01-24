package com.trend.feature_accounts.domain.usecases

import com.trend.feature_accounts.data.repositories.AccountsRepository
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.UsersModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUsersUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
): UpdateUsersUseCase {
    override suspend fun invoke(account: UsersModel): Flow<GenericResponse> {
        return accountsRepository.updateAccount(account)
    }
}