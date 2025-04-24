package com.tymex.interview.user_data.di

import com.tymex.interview.core.di.namedIO
import com.tymex.interview.user_data.repository.ListUserRepository
import com.tymex.interview.user_data.repository.ListUserRepositoryImpl
import com.tymex.interview.user_data.repository.UserDetailRepository
import com.tymex.interview.user_data.repository.UserDetailRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        ListUserRepositoryImpl(
            get(),
            get(qualifier = namedIO())
        )
    } bind ListUserRepository::class

    factory {
        UserDetailRepositoryImpl(
            get(),
            get(qualifier = namedIO())
        )
    } bind UserDetailRepository::class
}