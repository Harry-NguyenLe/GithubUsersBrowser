package com.tymex.interview.user_data.repository

import com.tymex.interview.user_domain.model.UserDetail

interface UserDetailRepository {
    suspend fun getUserDetail(userName: String): UserDetail
}