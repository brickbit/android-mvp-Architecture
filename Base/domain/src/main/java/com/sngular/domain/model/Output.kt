package com.sngular.domain.model

sealed class Output {
    data class Success<T>(val value: T) : Output()
    sealed class Error: Throwable() {
        data class Default(val messageError: String = ""): Error()
        data class NetworkError(val code: Int): Error()
    }
}
