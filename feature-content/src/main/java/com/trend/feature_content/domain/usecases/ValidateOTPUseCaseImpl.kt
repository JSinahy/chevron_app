package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_content.data.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ValidateOTPUseCaseImpl @Inject constructor(
    private val validateOTPRepository: AuthenticationRepository
): ValidateOTPUseCase {
    override suspend fun invoke(request: CheckOTPModel): Flow<GenericResponse> {
        return validateOTPRepository.validateOTP(request)
    }
}