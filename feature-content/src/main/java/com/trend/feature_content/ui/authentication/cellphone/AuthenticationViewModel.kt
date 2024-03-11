package com.trend.feature_authentication.ui.cellphone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_common.models.LoginModel
import com.trend.feature_common.models.LoginResponse
import com.trend.feature_common.network.BaseEvent
import com.trend.feature_content.domain.usecases.LoginWithCellNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginWithNumberCellNumberUseCase: LoginWithCellNumberUseCase
) : ViewModel() {

    private val _login: MutableStateFlow<BaseEvent<LoginResponse>> = MutableStateFlow(BaseEvent.Init)
    val login: StateFlow<BaseEvent<LoginResponse>> = _login

    fun loginWithCellNumber(request: LoginModel) {
        viewModelScope.launch {
            _login.emit(BaseEvent.Loading)
            try {
                loginWithNumberCellNumberUseCase.invoke(request).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _login.emit(BaseEvent.Success(it))
                    } ?: run {
                        _login.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _login.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}