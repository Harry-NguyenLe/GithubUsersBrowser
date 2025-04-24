package com.tymex.interview.user_ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tymex.interview.user_domain.usecase.SaveLoginUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val saveUserLoginUseCase: SaveLoginUserUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun saveLoginUser(username: String, password: String) {
        viewModelScope.launch {
            if (username.isBlank() || password.isBlank()) {
                _uiState.update {
                    it.copy(
                        errorMessage = "Username and password cannot be empty"
                    )
                }
            } else {
                _uiState.update {
                    it.copy(isLoading = true)
                }

                delay(1000) // Simulate network delay

                saveUserLoginUseCase.invoke(username)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoggedIn = true
                    )
                }
            }
        }
    }

    fun onUsernameChange(newUsername: String) {
        _uiState.update {
            it.copy(username = newUsername)
        }
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    fun toggleMode(isLoginMode: Boolean) {
        _uiState.update {
            it.copy(isLoginMode = !isLoginMode)
        }
    }
}