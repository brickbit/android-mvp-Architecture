package com.sngular.domain.contractor

import com.sngular.domain.model.CourseDto

interface MainContractor: BaseContractor {
    fun onGetCourseSuccess(courses: List<CourseDto>)
    fun onGetCourseError(error: String)
}