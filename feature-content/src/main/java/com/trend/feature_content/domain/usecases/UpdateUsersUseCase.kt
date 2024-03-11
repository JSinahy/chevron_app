package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.UsersModel
import kotlinx.coroutines.flow.Flow

interface UpdateUsersUseCase {
    suspend fun invoke(account: UsersModel): Flow<GenericResponse>
}