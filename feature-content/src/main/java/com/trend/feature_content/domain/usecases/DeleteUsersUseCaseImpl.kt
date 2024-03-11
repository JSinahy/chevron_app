package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_content.data.repositories.AccountsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteUsersUseCaseImpl @Inject constructor(
    private val accountsRepository: AccountsRepository
): DeleteUsersUseCase {
    override suspend fun invoke(account: DeleteProfile): Flow<GenericResponse> {
        return accountsRepository.deleteAccount(account)
    }
}