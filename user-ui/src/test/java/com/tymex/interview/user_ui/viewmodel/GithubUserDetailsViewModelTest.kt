package com.tymex.interview.user_ui.viewmodel

import com.tymex.interview.core.utils.Resource
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_domain.usecase.GetUserDetailUseCase
import com.tymex.interview.user_ui.dummy.dummyUserDetail
import com.tymex.interview.user_ui.screen.detail.GithubUserDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@OptIn(ExperimentalCoroutinesApi::class)
class GithubUserDetailsViewModelTest: BaseUnitTest() {

    private val mockUseCase: GetUserDetailUseCase = mockk(relaxed = true)
    private lateinit var viewModel: GithubUserDetailsViewModel


    override fun setUp() {
        super.setUp()
        viewModel = GithubUserDetailsViewModel(mockUseCase)
    }

    @Test
    fun `getUserDetail emits success UI state`() = runTest {
        // Given
        val userDetail = dummyUserDetail

        coEvery { mockUseCase.invoke("harry") } returns Resource.Success(userDetail)

        // When
        viewModel.getUserDetail("harry")

        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Harry", state.userName)
        assertEquals("https://example.com/avatar.png", state.avatarUrl)
        assertEquals("https://blog.example.com", state.blog)
        assertEquals("Vietnam", state.location)
        assertEquals("1200+", state.followers) // depends on your formatCountAbbreviated()
        assertEquals("5600+", state.following)
        assertNull(state.exception)
    }

    @Test
    fun `getUserDetail emits failure UI state`() = runTest {
        // Given
        val error = RuntimeException("Network error")
        coEvery { mockUseCase.invoke("harry") } returns Resource.Failure(error)

        // When
        viewModel.getUserDetail("harry")

        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(error, state.exception)
    }
}
