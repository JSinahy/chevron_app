package com.trend.feature_accounts.domain.usecases

import com.trend.feature_accounts.data.repositories.AccountsRepository
import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUsersUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
): DeleteUsersUseCase {
    override suspend fun invoke(account: DeleteProfile): Flow<GenericResponse> {
        return accountsRepository.deleteAccount(account)
    }
}