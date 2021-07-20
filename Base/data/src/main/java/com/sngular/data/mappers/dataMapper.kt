package com.sngular.data.mappers

import com.sngular.data.model.CourseAttributesDao
import com.sngular.data.model.CourseDao
import com.sngular.domain.model.CourseAttributesDto
import com.sngular.domain.model.CourseDto

fun CourseDto.toDao() = CourseDao(
    this.courseId,
    this.attributes.toDao()
)

fun CourseDao.toDto() = CourseDto(
    this.courseId,
    this.attributes.toDto()
)

fun CourseAttributesDto.toDao() = CourseAttributesDao(
    this.name,
    this.description,
    this.artworkUrl,
    this.difficulty,
    this.contributor,
)

fun CourseAttributesDao.toDto() = CourseAttributesDto(
    this.name,
    this.description,
    this.artworkUrl,
    this.difficulty,
    this.contributor,
)