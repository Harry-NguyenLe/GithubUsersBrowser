package com.githubusersbrowser.user_data.repository

import com.githubusersbrowser.core.utils.Resource
import com.githubusersbrowser.user_data.extension.handleAPICall
import com.githubusersbrowser.user_data.mapper.toDomain
import com.githubusersbrowser.user_data.remote.ApiService
import com.githubusersbrowser.user_domain.model.UserDetail
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