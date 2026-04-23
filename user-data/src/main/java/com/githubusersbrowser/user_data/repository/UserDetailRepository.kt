package com.githubusersbrowser.user_data.repository

import com.githubusersbrowser.user_domain.model.UserDetail

interface UserDetailRepository {
    suspend fun getUserDetail(userName: String): UserDetail
}