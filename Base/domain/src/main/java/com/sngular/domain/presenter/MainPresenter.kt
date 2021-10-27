package com.sngular.domain.presenter

import com.sngular.domain.contractor.MainContractor
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.executor.CoroutineExecutor
import com.sngular.domain.model.Output
import com.sngular.domain.repository.CourseRepository
import kotlinx.coroutines.launch

class MainPresenter(
    private val repository: CourseRepository,
    errorManager: ErrorManager,
    executor: CoroutineExecutor,
    view: MainContractor
) : BasePresenter<MainContractor>(
    errorManager = errorManager,
    executor = executor,
    view = view
) {
    override fun start() {
        super.start()
        getCourse()
    }

    private fun getCourse() {
        scope.launch {
            view.showProgress()
            execute { repository.getCourses() }.fold(
                onFailure = {
                    val error = errorManager.convert(it as Output.Error.NetworkError)
                    view.onGetCourseError(error)
                },
                onSuccess = { view.onGetCourseSuccess(it) }
            )
            view.hideProgress()
        }
    }
}