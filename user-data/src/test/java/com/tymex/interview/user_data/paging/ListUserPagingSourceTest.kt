package com.tymex.interview.user_data.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_data.dummy.dummyUserResponse
import com.tymex.interview.user_data.mapper.toDomain
import com.tymex.interview.user_data.remote.ApiService
import com.tymex.interview.user_data.remote.response.UserResponse
import com.tymex.interview.user_domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.Assert.assertEquals
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.Test
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class ListUserPagingSourceTest : BaseUnitTest() {

    private val apiService = mockk<ApiService>()
    private lateinit var pagingSource: ListUserPagingSource

    override fun setUp() {
        super.setUp()
        pagingSource = ListUserPagingSource(apiService, testDispatcher)
    }

    @Test
    fun `load returns Page on successful load`() = runTest(testDispatcher) {
        // Arrange
        val userResponses = dummyUserResponse
        coEvery { apiService.getUsers(any(), any()) } returns userResponses

        // Act
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        // Assert
        val expected = PagingSource.LoadResult.Page(
            data = userResponses.map { it.toDomain() },
            prevKey = null,
            nextKey = 2
        )
        assertEquals(expected, result)
    }

    @Test
    fun `load returns Error on IOException`() = runTest(testDispatcher) {
        coEvery { apiService.getUsers(any(), any()) } throws IOException()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns Error on HttpException`() = runTest(testDispatcher) {
        val exception = HttpException(
            Response.error<Any>(
                500,
                "Internal Server Error".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        )
        coEvery { apiService.getUsers(any(), any()) } throws exception

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `getRefreshKey returns null when anchorPosition is null`() {
        val state = PagingState<Int, User>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 20),
            leadingPlaceholderCount = 0
        )

        val result = pagingSource.getRefreshKey(state)
        assertEquals(GITHUB_STARTING_KEY, result)
    }
}
