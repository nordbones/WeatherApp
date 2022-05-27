package com.nordbones.weatherapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>
    data class Error(val exception: Throwable? = null) : ResultWrapper<Nothing>
    object Loading : ResultWrapper<Nothing>
}

fun <T, R> ResultWrapper<T>.onSuccess(map: (T) -> R): ResultWrapper<R> {
    return when (this) {
        is ResultWrapper.Error -> ResultWrapper.Error(this.exception)
        ResultWrapper.Loading -> ResultWrapper.Loading
        is ResultWrapper.Success -> ResultWrapper.Success(map(this.data))
    }
}

fun <T> Flow<T>.asResult(): Flow<ResultWrapper<T>> =
    this.map<T, ResultWrapper<T>> { ResultWrapper.Success(it) }
        .onStart { emit(ResultWrapper.Loading) }
        .catch { emit(ResultWrapper.Error(it)) }