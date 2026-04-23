package com.githubusersbrowser.user_data.usecase

import com.githubusersbrowser.core.local.Prefs
import com.githubusersbrowser.user_domain.usecase.SaveLoginUserUseCase

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