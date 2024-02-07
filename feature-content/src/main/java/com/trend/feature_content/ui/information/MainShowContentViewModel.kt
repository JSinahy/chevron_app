package com.trend.feature_content.ui.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.UpdateLessonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainShowContentViewModel @Inject constructor(
    private val updateLessonUseCase: UpdateLessonUseCase
): ViewModel() {
    private val _update: MutableStateFlow<BaseEvent<StatusLessonsResponse>> =  MutableStateFlow(BaseEvent.Init)
    val update: StateFlow<BaseEvent<StatusLessonsResponse>> = _update

    fun updateStatusLesson(request: StatusLessonsRequest) {
        viewModelScope.launch {
            _update.emit(BaseEvent.Loading)
            try {
                updateLessonUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _update.emit(BaseEvent.Success(it))
                    } ?: run {
                        _update.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _update.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}