package com.tymex.interview.user_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tymex.interview.user_domain.usecase.GetLoginUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    useCase: GetLoginUserUseCase
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    init {
        viewModelScope.launch {
            val result = useCase.invoke().first()
            _isLoggedIn.value = result
        }
    }
}