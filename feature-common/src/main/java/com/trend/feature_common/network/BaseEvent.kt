package com.trend.feature_common.network

sealed interface BaseEvent<out T> {
    object Init : BaseEvent<Nothing>
    object Loading : BaseEvent<Nothing>
    data class Success<T>(val data: T): BaseEvent<T>
    data class Error(val message: String) : BaseEvent<Nothing>
}