package com.sngular.base.error

import android.content.Context
import com.sngular.base.R
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.model.Result

class ErrorManagerImpl(val context: Context): ErrorManager {
    override fun convert(error: Result.Error): String =
        when (error) {
            Result.Error.NetworkError(500) -> context.getString(R.string.error_get_course)
            Result.Error.NetworkError(400) -> context.getString(R.string.error_get_course)
            else -> context.getString(R.string.default_error)
        }
}
