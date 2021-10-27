package com.sngular.domain.presenter

import com.sngular.domain.contractor.BaseContractor
import com.sngular.domain.model.Either
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.executor.CoroutineExecutor
import com.sngular.domain.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

abstract class BasePresenter<out V : BaseContractor>(
    val view: V,
    protected val errorManager: ErrorManager,
    protected val executor: CoroutineExecutor,
) {

    private val job = SupervisorJob()

    protected val scope = CoroutineScope(job + executor.main)

    open fun start() {}

    fun stop() = job.cancel()

    protected suspend fun <T> execute(f: suspend () -> Either<Result.Error, T>): Either<Result.Error, T> =
        withContext(executor.io) { f() }

}