package com.sngular.domain.error

interface ErrorManager {
    fun convert(error: com.sngular.domain.model.Output.Error): String
}