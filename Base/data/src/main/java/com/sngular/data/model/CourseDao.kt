package com.sngular.data.model

import com.google.gson.annotations.SerializedName

data class CourseDao (
    @SerializedName("id")
    val courseId: String,
    @SerializedName("attributes")
    val attributes: CourseAttributesDao,

)