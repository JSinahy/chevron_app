package com.trend.feature_content.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.MainContentRequest
import com.trend.feature_common.models.MainContentResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.ContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val contentUseCase: ContentUseCase
) : ViewModel() {

    private val _maincontent: MutableStateFlow<BaseEvent<MainContentResponse>> = MutableStateFlow(BaseEvent.Init)
    val maincontent: StateFlow<BaseEvent<MainContentResponse>> = _maincontent

    fun getMainContent(request: MainContentRequest) {
        viewModelScope.launch {
            _maincontent.emit(BaseEvent.Loading)
            try {
                contentUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _maincontent.emit(BaseEvent.Success(it))
                    } ?: run {
                        _maincontent.emit(BaseEvent.Error(it.message))
                    }

                }
            } catch(ex: Exception) {
                _maincontent.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}