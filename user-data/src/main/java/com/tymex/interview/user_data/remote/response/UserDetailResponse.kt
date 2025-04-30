package com.tymex.interview.user_data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tymex.interview.core.model.ResponseModel

@JsonClass(generateAdapter = true)
data class UserDetailResponse(

    @Json(name = "login")
    val login: String?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "avatar_url")
    val avatarUrl: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "blog")
    val blog: String?,

    @Json(name = "location")
    val location: String?,

    @Json(name = "followers")
    val followers: Int?,

    @Json(name = "following")
    val following: Int?,
) : ResponseModel
