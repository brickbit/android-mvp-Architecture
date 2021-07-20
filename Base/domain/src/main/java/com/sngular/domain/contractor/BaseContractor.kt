package com.sngular.domain.contractor

interface BaseContractor {
    fun showProgress()

    fun hideProgress()

    fun showError(error: String)

    fun showError(errorId: Int)

    fun showMessage(message: String)

    fun showMessage(messageId: Int)
}
