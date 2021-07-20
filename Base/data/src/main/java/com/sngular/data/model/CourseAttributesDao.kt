package com.sngular.data.model

import com.google.gson.annotations.SerializedName

data class CourseAttributesDao(
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("card_artwork_url")
    val artworkUrl:String?,
    @SerializedName("difficulty")
    val difficulty: String?,
    @SerializedName("contributor_string")
    val contributor:String?,
)