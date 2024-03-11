package com.trend.feature_content.ui.examenes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.StatusLessonsRequest
import com.trend.feature_common.models.StatusLessonsResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.UpdateStatusLessonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamenResultViewModel @Inject constructor(
    private val updateStatusLessonsUseCase: UpdateStatusLessonsUseCase
): ViewModel() {
    private val _update: MutableStateFlow<BaseEvent<StatusLessonsResponse>> =  MutableStateFlow(BaseEvent.Init)
    val update: StateFlow<BaseEvent<StatusLessonsResponse>> = _update

    fun updateStatusLessons(request: StatusLessonsRequest) {
        viewModelScope.launch {
            _update.emit(BaseEvent.Loading)
            try {
                updateStatusLessonsUseCase.invoke(request).collect {
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