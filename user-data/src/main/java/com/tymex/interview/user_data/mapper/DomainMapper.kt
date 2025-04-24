package com.tymex.interview.user_data.mapper

import com.tymex.interview.user_data.remote.response.UserDetailResponse
import com.tymex.interview.user_data.remote.response.UserResponse
import com.tymex.interview.user_domain.model.User
import com.tymex.interview.user_domain.model.UserDetail

fun UserResponse.toDomain() = User(
    login,
    id,
    avatarUrl,
    url,
)

fun UserDetailResponse.toDomain() = UserDetail(
    login,
    id,
    avatarUrl,
    name,
    blog,
    location,
    followers,
    following,
)