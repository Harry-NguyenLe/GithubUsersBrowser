package com.githubusersbrowser.user_domain.usecase

import androidx.paging.PagingData
import com.githubusersbrowser.user_domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserPagingUseCase {
    operator fun invoke(): Flow<PagingData<User>>
}