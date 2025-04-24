package com.tymex.interview.user_ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tymex.interview.core.utils.Resource
import com.tymex.interview.core.utils.formatCountAbbreviated
import com.tymex.interview.core.utils.orZero
import com.tymex.interview.user_domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GithubUserDetailsViewModel(
    private val useCase: GetUserDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    fun getUserDetail(username: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = useCase.invoke(username)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            blog = result.data.blog.orEmpty(),
                            userName = result.data.name.orEmpty(),
                            avatarUrl = result.data.avatarUrl.orEmpty(),
                            location = result.data.location.orEmpty(),
                            followers = result.data.followers.formatCountAbbreviated(),
                            following = result.data.following.formatCountAbbreviated()
                        )
                    }
                }

                is Resource.Failure -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            exception = result.error
                        )
                    }
                }
            }
        }
    }
}