package com.sngular.data.constants

import com.sngular.data.BuildConfig

class Constant {
    companion object {
        // Api - Base
        const val BASE_URL = BuildConfig.API_BASE
        const val PINNER_SHA = BuildConfig.PINNER_SHA

        const val GET_COURSES = BASE_URL + "contents?filter[content_types][]=collection"
        fun endpointUrl(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> BASE_URL
            BuildType.RELEASE -> BASE_URL
        }
    }

}

fun buildType(type: String): BuildType = when (type) {
    "debug" -> BuildType.DEBUG
    else -> BuildType.RELEASE
}

enum class BuildType {
    DEBUG, RELEASE
}