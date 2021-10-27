package com.sngular.data.repository

import com.sngular.data.datasource.network.NetworkDataSource
import com.sngular.data.mappers.toDto
import com.sngular.domain.model.CourseDto
import com.sngular.domain.model.Output
import com.sngular.domain.repository.CourseRepository

class CourseRepositoryImpl(private val network: NetworkDataSource): CourseRepository {

    override suspend fun getCourses(): Result<List<CourseDto>> {
        val apiResult = network.api.getCourses()

        return if (apiResult.isSuccessful) {
            Result.success(apiResult.body()!!.data.map { it!!.toDto() })
        } else {
            Result.failure(Output.Error.NetworkError(apiResult.code()))
        }
    }
}