package com.githubusersbrowser.di

import com.githubusersbrowser.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val activityViewModelModule = module {
    viewModel { MainViewModel(get()) }
}