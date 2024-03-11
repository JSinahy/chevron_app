package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import com.trend.feature_content.data.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginWithCellNumberUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): LoginWithCellNumberUseCase  {
    override suspend fun invoke(request: LoginModel): Flow<LoginResponse> {
        return authenticationRepository.loginWithCellNumber(request)
    }
}