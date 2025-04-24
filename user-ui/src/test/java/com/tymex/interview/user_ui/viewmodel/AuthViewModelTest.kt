package com.tymex.interview.user_ui.viewmodel

import com.tymex.interview.shared_test.base.BaseUnitTest
import com.tymex.interview.user_domain.usecase.SaveLoginUserUseCase
import com.tymex.interview.user_ui.screen.login.AuthViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest: BaseUnitTest() {
    
    private lateinit var viewModel: AuthViewModel
    private val mockSaveLoginUserUseCase: SaveLoginUserUseCase = mockk(relaxed = true)
    
    
    override fun setUp() {
        super.setUp()
        viewModel = AuthViewModel(mockSaveLoginUserUseCase)
    }


    @Test
    fun `saveLoginUser should show error for blank username or password`() = runTest(testDispatcher) {
        // Given
        viewModel.saveLoginUser("", "")

        advanceUntilIdle()

        // When
        val state = viewModel.uiState.value

        // Then
        assertEquals("Username and password cannot be empty", state.errorMessage)
        assertFalse(state.isLoading)
        assertFalse(state.isLoggedIn)
    }

    @Test
    fun `saveLoginUser should save login and update state on success`() = runTest(testDispatcher) {
        // Given
        coEvery { mockSaveLoginUserUseCase.invoke(any()) } returns Unit

        viewModel.saveLoginUser("harry", "secret")
        advanceTimeBy(1000)
        advanceUntilIdle()

        // When
        val state = viewModel.uiState.value

        // Then
        assertFalse(state.isLoading)
        assertTrue(state.isLoggedIn)
        assertNull(state.errorMessage)
        coVerify { mockSaveLoginUserUseCase.invoke("harry") }
    }

    @Test
    fun `onUsernameChange updates username`() {
        // When
        viewModel.onUsernameChange("harry")

        // Then
        assertEquals("harry", viewModel.uiState.value.username)
    }

    @Test
    fun `onPasswordChange updates password`() {
        // When
        viewModel.onPasswordChange("password123")

        // Then
        assertEquals("password123", viewModel.uiState.value.password)
    }

    @Test
    fun `toggleMode toggles login mode`() {
        // When
        val original = viewModel.uiState.value.isLoginMode
        viewModel.toggleMode(original)

        // Then
        assertEquals(!original, viewModel.uiState.value.isLoginMode)
    }
}
