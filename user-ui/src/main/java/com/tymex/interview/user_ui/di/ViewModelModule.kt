package com.tymex.interview.user_ui.di

import com.tymex.interview.user_ui.screen.detail.GithubUserDetailsViewModel
import com.tymex.interview.user_ui.screen.list.GithubUsersViewModel
import com.tymex.interview.user_ui.screen.login.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GithubUsersViewModel(get()) }
    viewModel { GithubUserDetailsViewModel(get()) }
    viewModel { AuthViewModel(get()) }
}