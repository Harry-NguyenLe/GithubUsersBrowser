package com.githubusersbrowser.user_data.usecase

import com.githubusersbrowser.core.utils.Resource
import com.githubusersbrowser.user_data.repository.UserDetailRepository
import com.githubusersbrowser.user_domain.model.UserDetail
import com.githubusersbrowser.user_domain.usecase.GetUserDetailUseCase

class GetUserDetailUseCaseImpl(
    private val repo: UserDetailRepository
) : GetUserDetailUseCase {
    override suspend fun invoke(userName: String): Resource<UserDetail> {
        return try {
            val result = repo.getUserDetail(userName)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}