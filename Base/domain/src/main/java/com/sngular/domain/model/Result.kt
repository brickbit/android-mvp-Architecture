package com.sngular.domain.model

class Result {
    sealed class Result {
        data class Success<T>(val value: T) : Result()
        sealed class Error : Result() {
            data class Default(val messageError: String = ""): Error()
            object GetCourseError : Error()
            object NoInternet : Error()
        }
    }
}