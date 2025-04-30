package com.tymex.interview.user_data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.tymex.interview.core.model.ResponseModel

/**
 * Data Transfer Object representing the detailed user information
 * received from the API endpoint: GET /users
 */
@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "login")
    val login: String?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "avatar_url")
    val avatarUrl: String? = null,

    @Json(name = "url")
    val url: String? = null,
) : ResponseModel