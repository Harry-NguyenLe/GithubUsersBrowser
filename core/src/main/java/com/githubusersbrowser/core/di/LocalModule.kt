package com.githubusersbrowser.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.githubusersbrowser.core.local.Prefs
import com.githubusersbrowser.core.local.PrefsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_prefs")

val localModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }
    single<Prefs> { PrefsImpl(get()) }
}