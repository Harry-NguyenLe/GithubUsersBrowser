package com.githubusersbrowser.user_data.di

import com.githubusersbrowser.core.di.namedIO
import com.githubusersbrowser.user_data.repository.ListUserRepository
import com.githubusersbrowser.user_data.repository.ListUserRepositoryImpl
import com.githubusersbrowser.user_data.repository.UserDetailRepository
import com.githubusersbrowser.user_data.repository.UserDetailRepositoryImpl
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