package com.tymex.interview.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.tymex.interview.core.utils.IS_LOGGED_IN
import com.tymex.interview.core.utils.USERNAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefsImpl(private val dataStore: DataStore<Preferences>) : Prefs {

    override val isLoggedIn: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }
        .catch { exception ->
            if (exception is IOException) {
                emit(false)
            } else {
                throw exception
            }
        }

    override suspend fun saveLoginStatus(isLoggedIn: Boolean, username: String?) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
            if (username != null) {
                preferences[USERNAME] = username
            } else {
                preferences.remove(USERNAME)
            }
        }
    }
}