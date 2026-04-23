package com.githubusersbrowser.user_data.remote

import com.githubusersbrowser.shared_test.base.BaseUnitTest
import com.githubusersbrowser.user_data.di.toJson
import com.githubusersbrowser.user_data.dummy.getUserDetailResponse
import com.githubusersbrowser.user_data.dummy.getUserResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ApiServiceTest : BaseUnitTest() {

    private lateinit var apiService: ApiService

    override fun setUp() {
        super.setUp()
        apiService = mockedServer.enqueueService<ApiService>()
    }

    override fun tearDown() {
        super.tearDown()
        mockedServer.shutdown()
    }

    @Test
    fun `getUsers returns expected list of UserResponse`() = runTest {
        // Given
        val expected = getUserResponse()
        enqueueResponse {
            setBody(expected.toJson())
        }

        // When
        val actual = apiService.getUsers(20, 100)
        mockedServer.takeRequest()

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `getUserDetail returns expected UserDetailResponse`() = runTest {
        // Given
        val username = "mojombo"
        val expected = getUserDetailResponse()
        enqueueResponse {
            setBody(expected.toJson())
        }

        // When
        val actual = apiService.getUserDetails(username)
        mockedServer.takeRequest()

        // Then
        assertEquals(expected, actual.body())
    }
}