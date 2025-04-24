package com.tymex.interview.user_data.usecase

import com.tymex.interview.core.local.Prefs
import com.tymex.interview.user_domain.usecase.SaveLoginUserUseCase

class SaveLoginUserUseCaseImpl(
    private val prefs: Prefs
) : SaveLoginUserUseCase {

    override suspend fun invoke(username: String) {
        prefs.saveLoginStatus(
            isLoggedIn = true,
            username = username
        )
    }
}