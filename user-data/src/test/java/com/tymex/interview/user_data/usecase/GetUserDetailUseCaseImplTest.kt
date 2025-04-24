package com.tymex.interview.user_data.usecase

import com.tymex.interview.core.utils.Resource
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_data.dummy.dummyUserDetail
import com.tymex.interview.user_data.repository.UserDetailRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Test

class GetUserDetailUseCaseImplTest : BaseUnitTest() {

    private lateinit var useCase: GetUserDetailUseCaseImpl
    private val mockRepo = mockk<UserDetailRepository>()

    override fun setUp() {
        super.setUp()
        useCase = GetUserDetailUseCaseImpl(mockRepo)
    }

    @Test
    fun `invoke called with valid username returns Resource Success`() = runTest {
        // Given
        coEvery { mockRepo.getUserDetail(any()) } returns dummyUserDetail

        // When
        val result = useCase.invoke("testUser")

        // Then
        assertTrue(result is Resource.Success)
        assertEquals(dummyUserDetail, (result as Resource.Success).data)
    }

    @Test
    fun `invoke returns Failure when repo throws exception`() = runTest {
        // Given
        val errorMessage = "An error occurred"
        val exception = Exception(errorMessage)
        coEvery { mockRepo.getUserDetail(any()) } throws exception

        // When
        val result = useCase.invoke("testUser")

        // Then
        assertTrue(result is Resource.Failure)
        assertEquals(exception, (result as Resource.Failure).error)
    }
}