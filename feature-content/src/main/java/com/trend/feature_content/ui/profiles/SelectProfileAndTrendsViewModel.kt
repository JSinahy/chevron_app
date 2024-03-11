package com.trend.feature_content.ui.profiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.TrendsCompletedResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.GetBrandsCompletedUseCase
//import com.trend.feature_content.domain.usecases.GetTrendsCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectProfileAndTrendsViewModel @Inject constructor(
    private val getTrendsCompletedUseCase: GetBrandsCompletedUseCase
): ViewModel() {

    private val _trends: MutableStateFlow<BaseEvent<TrendsCompletedResponse>> = MutableStateFlow(BaseEvent.Init)
    val trends: StateFlow<BaseEvent<TrendsCompletedResponse>> = _trends

    fun getRoadmapCompleted(request: Int) {
        viewModelScope.launch {
            _trends.emit(BaseEvent.Loading)
            try {
                getTrendsCompletedUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _trends.emit(BaseEvent.Success(it))
                    } ?: run {
                        _trends.emit(BaseEvent.Error(it.message))
                    }
                }

            } catch(ex: Exception) {
                _trends.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}