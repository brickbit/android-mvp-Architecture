package com.sngular.data.datasource.network

import com.sngular.domain.model.Either
import com.sngular.domain.model.Result.Result

suspend fun <R> execute(f: suspend () -> R): Either<Result.Error, R> =
    try {
        Either.Right(f())
    } catch (requestError: Throwable) {
        val error: Result.Error = Result.Error.Default(requestError.message ?: "")
        Either.Left(error)
    }