package com.tymex.interview.core.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
val USERNAME = stringPreferencesKey("username")