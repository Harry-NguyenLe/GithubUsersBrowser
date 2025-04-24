package com.tymex.interview.user_domain.usecase

import androidx.paging.PagingData
import com.tymex.interview.user_domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUserPagingUseCase {
    operator fun invoke(): Flow<PagingData<User>>
}