package com.tymex.interview.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.tymex.interview.core.local.Prefs
import com.tymex.interview.core.local.PrefsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

val localModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }
    single<Prefs> { PrefsImpl(get()) }
}