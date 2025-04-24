package com.tymex.interview.user_domain.usecase

import com.tymex.interview.core.utils.Resource
import com.tymex.interview.user_domain.model.UserDetail

interface GetUserDetailUseCase {
    suspend operator fun invoke(userName: String): Resource<UserDetail>
}