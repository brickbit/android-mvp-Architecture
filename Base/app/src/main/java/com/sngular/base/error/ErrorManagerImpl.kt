package com.sngular.base.error

import android.content.Context
import com.sngular.base.R
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.model.Output

class ErrorManagerImpl(val context: Context): ErrorManager {
    override fun convert(error: Output.Error): String =
        when (error) {
            Output.Error.NetworkError(300) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(400) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(401) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(403) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(404) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(405) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(408) -> context.getString(R.string.error_get_course)
            Output.Error.NetworkError(500) -> context.getString(R.string.error_get_course)
            else -> context.getString(R.string.default_error)
        }
}
