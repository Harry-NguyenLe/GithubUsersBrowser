package com.githubusersbrowser.user_domain.usecase

import com.githubusersbrowser.core.utils.Resource
import com.githubusersbrowser.user_domain.model.UserDetail

interface GetUserDetailUseCase {
    suspend operator fun invoke(userName: String): Resource<UserDetail>
}