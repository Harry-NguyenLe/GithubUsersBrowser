package com.githubusersbrowser.user_domain.usecase

interface SaveLoginUserUseCase {
    suspend operator fun invoke(username: String)
}