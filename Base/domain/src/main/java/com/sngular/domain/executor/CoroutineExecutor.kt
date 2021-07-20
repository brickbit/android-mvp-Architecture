package com.sngular.domain.executor

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineExecutor {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}