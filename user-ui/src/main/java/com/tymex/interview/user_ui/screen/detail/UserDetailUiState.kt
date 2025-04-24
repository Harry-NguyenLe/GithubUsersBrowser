package com.tymex.interview.user_ui.screen.detail

data class UserDetailUiState(
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val userName: String = "",
    val blog: String = "",
    val avatarUrl: String = "",
    val htmlUrl: String = "", // Landing page of the user
    val location: String = "",
    val followers: String = "",
    val following: String = ""
)
