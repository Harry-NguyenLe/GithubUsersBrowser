package com.tymex.interview.user_data.di

import com.tymex.interview.user_data.usecase.GetLoginUserUseCaseImpl
import com.tymex.interview.user_data.usecase.GetUserDetailUseCaseImpl
import com.tymex.interview.user_data.usecase.GetUserPagingUseCaseImpl
import com.tymex.interview.user_data.usecase.SaveLoginUserUseCaseImpl
import com.tymex.interview.user_domain.usecase.GetLoginUserUseCase
import com.tymex.interview.user_domain.usecase.GetUserDetailUseCase
import com.tymex.interview.user_domain.usecase.GetUserPagingUseCase
import com.tymex.interview.user_domain.usecase.SaveLoginUserUseCase
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetUserDetailUseCaseImpl(get()) } bind GetUserDetailUseCase::class
    factory { GetUserPagingUseCaseImpl(get()) } bind GetUserPagingUseCase::class
    factory { GetLoginUserUseCaseImpl(get()) } bind GetLoginUserUseCase::class
    factory { SaveLoginUserUseCaseImpl(get()) } bind SaveLoginUserUseCase::class
}