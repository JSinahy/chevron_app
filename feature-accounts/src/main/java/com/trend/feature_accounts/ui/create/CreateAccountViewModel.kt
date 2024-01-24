package com.trend.feature_accounts.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trend.feature_accounts.data.repositories.AccountsRepository
import com.trend.feature_accounts.domain.usecases.CitiesUseCase
import com.trend.feature_accounts.domain.usecases.CountriesUseCase
import com.trend.feature_accounts.domain.usecases.DeleteUsersUseCase
import com.trend.feature_accounts.domain.usecases.SaveUserUseCase
import com.trend.feature_accounts.domain.usecases.ServiceStationsUseCase
import com.trend.feature_accounts.domain.usecases.UpdateUsersUseCase
import com.trend.feature_common.models.CitiesRequest
import com.trend.feature_common.models.CitiesResponse
import com.trend.feature_common.models.CountriesResponse
import com.trend.feature_common.models.DeleteProfile
import com.trend.feature_common.models.GenericResponse
import com.trend.feature_common.models.SSRequest
import com.trend.feature_common.models.SSResponse
import com.trend.feature_common.models.UsersModel
import com.trend.feature_common.network.BaseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val countriesUseCase: CountriesUseCase,
    private val citiesUseCase: CitiesUseCase,
    private val serviceStationsUseCase: ServiceStationsUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val updateUsersUseCase: UpdateUsersUseCase,
    private val deleteUsersUseCase: DeleteUsersUseCase
): ViewModel() {
    private val _countries: MutableStateFlow<BaseEvent<CountriesResponse>> = MutableStateFlow(BaseEvent.Init)
    val countries : StateFlow<BaseEvent<CountriesResponse>> = _countries

    private val _cities: MutableStateFlow<BaseEvent<CitiesResponse>> = MutableStateFlow(BaseEvent.Init)
    val cities : StateFlow<BaseEvent<CitiesResponse>> = _cities

    private val _ss: MutableStateFlow<BaseEvent<SSResponse>> = MutableStateFlow(BaseEvent.Init)
    val ss : StateFlow<BaseEvent<SSResponse>> = _ss

    private val _createAccount: MutableStateFlow<BaseEvent<GenericResponse>> = MutableStateFlow(BaseEvent.Init)
    val createAccount : StateFlow<BaseEvent<GenericResponse>> = _createAccount

    private val _updateAccount: MutableStateFlow<BaseEvent<GenericResponse>> = MutableStateFlow(BaseEvent.Init)
    val updateAccount : StateFlow<BaseEvent<GenericResponse>> = _updateAccount

    private val _deleteAccount: MutableStateFlow<BaseEvent<GenericResponse>> = MutableStateFlow(BaseEvent.Init)
    val deleteAccount : StateFlow<BaseEvent<GenericResponse>> = _deleteAccount

    fun getCountries() {
        viewModelScope.launch {
            _countries.emit(BaseEvent.Loading)
            try {
                countriesUseCase.invoke().collect {
                    it.takeIf { it.status == 200 }?.also {
                        _countries.emit(BaseEvent.Success(it))
                    } ?: run {
                        _countries.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _countries.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }

    fun getCities(request: CitiesRequest) {
        viewModelScope.launch {
            _cities.emit(BaseEvent.Loading)
            try {
                citiesUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _cities.emit(BaseEvent.Success(it))
                    } ?: run {
                        _cities.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _cities.emit(BaseEvent.Error(ex.message.toString()))
            }

        }
    }

    fun getServiceStations(request: SSRequest) {
        viewModelScope.launch {
            _ss.emit(BaseEvent.Loading)
            try {
                serviceStationsUseCase.invoke(request).collect {
                    it.takeIf { it.status == 200 }?.also {
                        _ss.emit(BaseEvent.Success(it))
                    } ?: run {
                        _ss.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _ss.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }

    fun createAccount(account: UsersModel) {
        viewModelScope.launch {
            _createAccount.emit(BaseEvent.Loading)
            try {
                saveUserUseCase.invoke(account).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _createAccount.emit(BaseEvent.Success(it))
                    } ?: run {
                        _createAccount.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _createAccount.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }

    fun updateAccount(account: UsersModel) {
        viewModelScope.launch {
            _updateAccount.emit(BaseEvent.Loading)
            try {
                saveUserUseCase.invoke(account).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _updateAccount.emit(BaseEvent.Success(it))
                    } ?: run {
                        _updateAccount.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _updateAccount.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }

    fun deleteAccount(account: DeleteProfile) {
        viewModelScope.launch {
            _deleteAccount.emit(BaseEvent.Loading)
            try {
                deleteUsersUseCase.invoke(account).collect {
                    it.takeIf { it.status == 1 }?.also {
                        _deleteAccount.emit(BaseEvent.Success(it))
                    } ?: run {
                        _deleteAccount.emit(BaseEvent.Error(it.message))
                    }
                }
            } catch(ex: Exception) {
                _deleteAccount.emit(BaseEvent.Error(ex.message.toString()))
            }
        }
    }
}