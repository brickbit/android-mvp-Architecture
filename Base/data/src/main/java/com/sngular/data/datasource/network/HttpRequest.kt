package com.sngular.data.datasource.network


inline fun <R, reified E: Throwable> execute(f: () -> R): Result<R>? =
    try {
        Result.success(f())
    } catch (requestError: Throwable) {
        if(requestError is E) {
            Result.failure(requestError)
        } else {
            null
        }
    }