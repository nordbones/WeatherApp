package com.nordbones.weatherapp.utils

import kotlinx.coroutines.flow.*

sealed interface ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>
    data class Error<T>(val exception: Throwable? = null, val defaultData: T? = null) :
        ResultWrapper<T>

    object Loading : ResultWrapper<Nothing>
}

inline fun <T, R> ResultWrapper<T>.onSuccess(map: (T) -> R): ResultWrapper<R> {
    return when (this) {
        is ResultWrapper.Error -> ResultWrapper.Error(this.exception)
        ResultWrapper.Loading -> ResultWrapper.Loading
        is ResultWrapper.Success -> ResultWrapper.Success(map(this.data))
    }
}

inline fun <T, R> ResultWrapper<T>.onError(map: () -> R): ResultWrapper<R> {
    return when (this) {
        is ResultWrapper.Error -> ResultWrapper.Error(this.exception, map())
        ResultWrapper.Loading -> ResultWrapper.Loading
        is ResultWrapper.Success -> ResultWrapper.Success(map())
    }
}

fun <T> Flow<T>.asResult(): Flow<ResultWrapper<T>> =
    this.transform {
        emit(ResultWrapper.Loading)
        emit(ResultWrapper.Success(it))
    }
        .catch { emit(ResultWrapper.Error(it)) }