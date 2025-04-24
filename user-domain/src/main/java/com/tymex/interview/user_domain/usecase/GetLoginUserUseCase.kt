package com.tymex.interview.user_domain.usecase

import kotlinx.coroutines.flow.Flow

interface GetLoginUserUseCase {
    operator fun invoke(): Flow<Boolean>
}