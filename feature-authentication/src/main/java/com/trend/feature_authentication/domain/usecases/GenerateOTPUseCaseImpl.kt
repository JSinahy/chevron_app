package com.trend.feature_authentication.domain.usecases

import com.trend.feature_authentication.data.repositories.AuthenticationRepository
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateOTPUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
): GenerateOTPUseCase {
    override suspend fun invoke(request: GenerateOTPModel): Flow<GenerateOTPResponse> {
        return authenticationRepository.generateOTP(request)
    }
}