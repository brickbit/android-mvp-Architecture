package com.sngular.data.api

import com.sngular.data.BuildConfig
import com.sngular.data.constants.Constant
import com.sngular.data.constants.Constant.Companion.endpointUrl
import com.sngular.data.constants.buildType
import com.sngular.data.model.CourseDao
import com.sngular.data.model.ListCoursesDao

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    companion object {
        val ENDPOINT = endpointUrl(buildType(BuildConfig.BUILD_TYPE))
    }

    @GET(Constant.GET_COURSES)
    suspend fun getCourses(
    ): ListCoursesDao
}