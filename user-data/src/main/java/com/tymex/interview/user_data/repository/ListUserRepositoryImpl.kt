package com.tymex.interview.user_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tymex.interview.user_data.paging.GITHUB_DEFAULT_PAGE_SIZE
import com.tymex.interview.user_data.paging.ListUserPagingSource
import com.tymex.interview.user_data.remote.ApiService
import com.tymex.interview.user_domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class ListUserRepositoryImpl(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : ListUserRepository {

    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = GITHUB_DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ListUserPagingSource(apiService, dispatcher)
            }
        ).flow
    }
}

