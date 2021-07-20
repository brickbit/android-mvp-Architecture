package com.sngular.domain.repository

import com.sngular.domain.model.CourseDto
import com.sngular.domain.model.Either
import com.sngular.domain.model.Result

interface CourseRepository {
    suspend fun getCourses(): Either<Result.Result.Error, List<CourseDto>>
}