package com.githubusersbrowser.user_data.mapper

import com.githubusersbrowser.user_data.remote.response.UserDetailResponse
import com.githubusersbrowser.user_data.remote.response.UserResponse
import com.githubusersbrowser.user_domain.model.User
import com.githubusersbrowser.user_domain.model.UserDetail

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