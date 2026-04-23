package com.githubusersbrowser.user_data.usecase

import com.githubusersbrowser.core.local.Prefs
import com.githubusersbrowser.user_domain.usecase.GetLoginUserUseCase
import kotlinx.coroutines.flow.Flow

class GetLoginUserUseCaseImpl(
    private val prefs: Prefs
) : GetLoginUserUseCase {

    override fun invoke(): Flow<Boolean> =
        prefs.isLoggedIn
}