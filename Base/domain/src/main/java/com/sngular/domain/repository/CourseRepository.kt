package com.sngular.domain.repository

import com.sngular.domain.model.CourseDto

interface CourseRepository {
    suspend fun getCourses(): Result<List<CourseDto>>
}