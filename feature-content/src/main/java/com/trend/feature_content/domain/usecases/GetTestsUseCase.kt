package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import kotlinx.coroutines.flow.Flow

interface GetTestsUseCase {
    suspend fun invoke(request: TestRequest): Flow<TestResponse>
}