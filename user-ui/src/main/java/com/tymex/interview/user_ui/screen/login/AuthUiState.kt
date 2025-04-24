package com.tymex.interview.user_ui.screen.login

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val username: String = "",
    val password: String = "",
    val isLoginMode: Boolean = true,
    val errorMessage: String? = null,
)
