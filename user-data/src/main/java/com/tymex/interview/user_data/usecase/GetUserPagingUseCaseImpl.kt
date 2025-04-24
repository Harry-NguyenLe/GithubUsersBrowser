package com.tymex.interview.user_data.usecase

import androidx.paging.PagingData
import com.tymex.interview.user_data.repository.ListUserRepository
import com.tymex.interview.user_domain.model.User
import com.tymex.interview.user_domain.usecase.GetUserPagingUseCase
import kotlinx.coroutines.flow.Flow

class GetUserPagingUseCaseImpl(
    private val repository: ListUserRepository
) : GetUserPagingUseCase {

    override fun invoke(): Flow<PagingData<User>> {
        return repository.getUsers()
    }
}