package com.tymex.interview.core.local

import kotlinx.coroutines.flow.Flow

interface Prefs {
    val isLoggedIn: Flow<Boolean>
    suspend fun saveLoginStatus(isLoggedIn: Boolean, username: String?)
}