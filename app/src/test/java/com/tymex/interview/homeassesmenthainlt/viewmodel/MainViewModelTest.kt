package com.tymex.interview.homeassesmenthainlt.viewmodel

import app.cash.turbine.test
import com.tymex.interview.homeassesmenthainlt.MainViewModel
import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_domain.usecase.GetLoginUserUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest : BaseUnitTest() {
    private lateinit var viewModel: MainViewModel
    private val mockGetLoginUserUseCase: GetLoginUserUseCase = mockk(relaxed = true)

    @Test
    fun `isLoggedIn returns true when user is logged in`() = runTest(testDispatcher) {
        // Given
        every { mockGetLoginUserUseCase.invoke() } returns flowOf(true)

        // When
        viewModel = MainViewModel(mockGetLoginUserUseCase)
        advanceUntilIdle()

        // Then
        viewModel.isLoggedIn.test {
            assertEquals(true, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `isLoggedIn returns false when user is not logged in`() = runTest(testDispatcher) {
        // Given
        every { mockGetLoginUserUseCase.invoke() } returns flowOf(false)

        // When
        viewModel = MainViewModel(mockGetLoginUserUseCase)
        advanceUntilIdle()

        // Then
        viewModel.isLoggedIn.test {
            assertEquals(false, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}