package com.trend.feature_tests.domain.usecases

import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import com.trend.feature_tests.data.repository.TestsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTestsUseCaseImpl @Inject constructor(
    private val testsRepository: TestsRepository
): GetTestsUseCase {
    override suspend fun invoke(request: TestRequest): Flow<TestResponse> {
        return testsRepository.getTests(request)
    }
}