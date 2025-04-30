package com.tymex.interview.homeassesmenthainlt.di

import com.tymex.interview.homeassesmenthainlt.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val activityViewModelModule = module {
    viewModel { MainViewModel(get()) }
}