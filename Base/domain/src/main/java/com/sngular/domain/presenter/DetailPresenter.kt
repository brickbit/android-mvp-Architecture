package com.sngular.domain.presenter

import com.sngular.domain.contractor.DetailContractor
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.executor.CoroutineExecutor

class DetailPresenter(
    errorManager: ErrorManager,
    executor: CoroutineExecutor,
    view: DetailContractor
) : BasePresenter<DetailContractor>(
    errorManager = errorManager,
    executor = executor,
    view = view
)