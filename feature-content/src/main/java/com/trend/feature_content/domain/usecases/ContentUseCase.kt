package com.trend.feature_content.domain.usecases

import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    suspend fun invoke(request: MainContentRequest): Flow<MainContentResponse>
}