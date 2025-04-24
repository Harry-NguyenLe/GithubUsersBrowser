package com.tymex.interview.user_data.repository

import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_data.dummy.dummyUserDetailResponse
import com.tymex.interview.user_data.mapper.toDomain
import com.tymex.interview.user_data.remote.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import retrofit2.Response
import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals

class UserDetailRepositoryImplTest : BaseUnitTest() {

    private val mockService = mockk<ApiService>(relaxed = true)
    private lateinit var repository: UserDetailRepositoryImpl

    override fun setUp() {
        super.setUp()
        repository = UserDetailRepositoryImpl(mockService, testDispatcher)
    }

    @Test
    fun `getUserDetail returns mapped domain model on success`() = runTest(testDispatcher) {
        // Given
        val userName = "testUser"
        val response = dummyUserDetailResponse
        coEvery { mockService.getUserDetails(userName) } returns Response.success(response)

        // When
        val result = repository.getUserDetail(userName)

        // Then
        assertEquals(response.toDomain(), result)
    }

    @Test
    fun `getUserDetail throws exception on API failure`() = runTest(testDispatcher) {
        // Given
        val userName = "testUser"
        val errorMgs = "Unknown error"
        val exception = IOException(errorMgs)
        coEvery { mockService.getUserDetails(userName) } throws exception

        // When
        try {
            repository.getUserDetail(userName)
        } catch (e: IOException) {
            // Then
            assertEquals(errorMgs, e.message)
        }
    }
}