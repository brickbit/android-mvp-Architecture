package com.sngular.data.repository

import com.sngular.data.datasource.network.NetworkDataSource
import com.sngular.data.mappers.toDto
import com.sngular.domain.model.CourseDto
import com.sngular.domain.model.Either
import com.sngular.domain.model.Result
import com.sngular.domain.repository.CourseRepository

class CourseRepositoryImpl(private val network: NetworkDataSource): CourseRepository {

    override suspend fun getCourses(): Either<Result.Error, List<CourseDto>> {
        val apiResult = network.api.getCourses()

        return if (apiResult.isSuccessful) {
            Either.Right(apiResult.body()!!.data.map { it!!.toDto() })
        } else {
            Either.Left(Result.Error.NetworkError(apiResult.code()))
        }
    }
}