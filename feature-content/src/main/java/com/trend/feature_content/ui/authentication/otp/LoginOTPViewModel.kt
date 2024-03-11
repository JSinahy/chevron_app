package com.trend.feature_authentication.ui.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.CheckOTPModel
import com.trend.feature_common.models.GenerateOTPModel
import com.trend.feature_common.models.GenerateOTPResponse
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.GenerateOTPUseCase
import com.trend.feature_content.domain.usecases.ValidateOTPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginOTPViewModel @Inject constructor(
    private val validateOTPUseCase: ValidateOTPUseCase,
    private val generateOTPUseCase: GenerateOTPUseCase
): ViewModel() {

    private val _validate: MutableStateFlow<BaseEvent<GenericResponse>> = MutableStateFlow(BaseEvent.Init)
    val validate: StateFlow<BaseEvent<GenericResponse>> = _validate

    private val _generated:  MutableStateFlow<BaseEvent<GenerateOTPResponse>> = MutableStateFlow(BaseEvent.Init)
    val generated: StateFlow<BaseEvent<GenerateOTPResponse>> = _generated

    fun validateOTP(request: CheckOTPModel) {
        viewModelScope.launch {
            _validate.emit(BaseEvent.Loading)
            try {
                validateOTPUseCase.invoke(request).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _validate.emit(BaseEvent.Success(it))
                    } ?: run {
                        _validate.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _validate.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }

    fun generateOTP(request: GenerateOTPModel) {
        viewModelScope.launch {
            _generated.emit(BaseEvent.Loading)
            try {
                generateOTPUseCase.invoke(request).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _generated.emit(BaseEvent.Success(it))
                    } ?: run {
                        _generated.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch (ex: Exception) {
                _generated.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}