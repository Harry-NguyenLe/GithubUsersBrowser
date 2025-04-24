package com.tymex.interview.user_data.repository

import androidx.paging.PagingData
import com.tymex.interview.user_domain.model.User
import kotlinx.coroutines.flow.Flow

interface ListUserRepository {
    fun getUsers(): Flow<PagingData<User>>
}