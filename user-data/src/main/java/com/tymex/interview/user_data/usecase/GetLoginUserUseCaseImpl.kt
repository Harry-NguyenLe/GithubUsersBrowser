package com.tymex.interview.user_data.usecase

import com.tymex.interview.core.local.Prefs
import com.tymex.interview.user_domain.usecase.GetLoginUserUseCase
import kotlinx.coroutines.flow.Flow

class GetLoginUserUseCaseImpl(
    private val prefs: Prefs
) : GetLoginUserUseCase {

    override fun invoke(): Flow<Boolean> =
        prefs.isLoggedIn
}