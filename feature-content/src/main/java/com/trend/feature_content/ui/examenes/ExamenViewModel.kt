package com.trend.feature_content.ui.examenes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.TestRequest
import com.trend.feature_common.models.TestResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.GetTestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExamenViewModel @Inject constructor(
    private val testsUseCase: GetTestsUseCase
): ViewModel() {
    private val _tests: MutableStateFlow<BaseEvent<TestResponse>> = MutableStateFlow(BaseEvent.Init)
    val tests: StateFlow<BaseEvent<TestResponse>> = _tests

    fun getTests(request: TestRequest) {

        viewModelScope.launch {
            _tests.emit(BaseEvent.Loading)
            try {
                testsUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _tests.emit(BaseEvent.Success(it))
                    } ?: run {
                        _tests.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception ) {
                _tests.emit(BaseEvent.Error(ex.message.toString()))
            }

        }
    }
}