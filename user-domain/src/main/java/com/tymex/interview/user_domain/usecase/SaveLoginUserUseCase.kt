package com.tymex.interview.user_domain.usecase

interface SaveLoginUserUseCase {
    suspend operator fun invoke(username: String)
}