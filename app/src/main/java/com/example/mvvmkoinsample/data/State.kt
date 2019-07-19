package com.example.mvvmkoinsample.data

sealed class State<T> {
    data class Loading<T>(var data: T? = null): State<T>()
    data class Success<T>(var data: T) : State<T>()
    data class Error<T>(var throwable: Throwable) : State<T>()
}