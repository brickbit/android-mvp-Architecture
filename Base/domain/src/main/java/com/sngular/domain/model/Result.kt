package com.sngular.domain.model

sealed class Result {
    data class Success<T>(val value: T) : Result()
    sealed class Error : Result() {
        data class Default(val messageError: String = ""): Error()
        data class NetworkError(val code: Int) : Error()
        object NoInternet : Error()
    }
}
