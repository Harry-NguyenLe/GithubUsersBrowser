package com.tymex.interview.user_data.usecase

import androidx.paging.PagingData
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_data.dummy.dummyUser
import com.tymex.interview.user_data.repository.ListUserRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserPagingUseCaseImplTest : BaseUnitTest() {
    private lateinit var useCase: GetUserPagingUseCaseImpl
    private val mockRepo = mockk<ListUserRepository>()

    override fun setUp() {
        super.setUp()
        useCase = GetUserPagingUseCaseImpl(mockRepo)
    }

    @Test
    fun `invoke called returns PagingData from repo`() = runTest {
        // Given
        val expectedPagingData = PagingData.from(listOf(dummyUser))
        every { mockRepo.getUsers() } returns flowOf(expectedPagingData)

        // When
        val result = useCase.invoke().first()

        // Then
        assertEquals(expectedPagingData, result)
    }
}