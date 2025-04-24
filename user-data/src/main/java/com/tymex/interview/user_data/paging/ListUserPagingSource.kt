package com.tymex.interview.user_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tymex.interview.user_data.mapper.toDomain
import com.tymex.interview.user_data.remote.ApiService
import com.tymex.interview.user_domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

const val GITHUB_STARTING_KEY: Int = 0
const val GITHUB_DEFAULT_PAGE_SIZE = 20

open class ListUserPagingSource(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: GITHUB_STARTING_KEY
        val loadSize = params.loadSize.coerceAtMost(GITHUB_DEFAULT_PAGE_SIZE)

        return try {
            withContext(dispatcher) {
                val response = apiService.getUsers(
                    perPage = loadSize,
                    since = position
                )
                val users = response.map { it.toDomain() }

                val nextKey = if (users.isEmpty()) {
                    null
                } else {
                    users.last().id
                }

                LoadResult.Page(
                    data = users,
                    prevKey = null,
                    nextKey = nextKey
                )
            }
        } catch (exception: IOException) {
            // IOException for network failures.
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            // HttpException for non-2xx responses.
            LoadResult.Error(exception)
        }
    }

    // Provides the key for the initial load for the new PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, User>): Int {
        return state.anchorPosition?.let {
            null
        } ?: GITHUB_STARTING_KEY // Fallback if anchorPosition is null
    }
}