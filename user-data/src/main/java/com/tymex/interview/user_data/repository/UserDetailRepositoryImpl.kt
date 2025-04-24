package com.tymex.interview.user_data.repository

import com.tymex.interview.core.utils.Resource
import com.tymex.interview.user_data.extension.handleAPICall
import com.tymex.interview.user_data.mapper.toDomain
import com.tymex.interview.user_data.remote.ApiService
import com.tymex.interview.user_domain.model.UserDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserDetailRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : UserDetailRepository {

    override suspend fun getUserDetail(userName: String): UserDetail =
        withContext(dispatcher) {
            val resource = handleAPICall { apiService.getUserDetails(userName) }
            when (resource) {
                is Resource.Success -> resource.data.toDomain()
                is Resource.Failure -> throw resource.error
            }
        }
}