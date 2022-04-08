package com.mechamanul.utils.composetmdb.base

sealed class Result<out T>{
    object Loading: Result<Nothing>()
    data class Success<out T>(val data:T):Result<T>()
    data class Error(val throwable: Throwable):Result<Nothing>()
}
