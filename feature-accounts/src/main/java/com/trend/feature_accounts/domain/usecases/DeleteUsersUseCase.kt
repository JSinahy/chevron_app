package com.trend.feature_accounts.domain.usecases

import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import kotlinx.coroutines.flow.Flow

interface DeleteUsersUseCase {
    suspend fun invoke(account: DeleteProfile): Flow<GenericResponse>
}