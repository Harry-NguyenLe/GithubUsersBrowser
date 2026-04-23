package com.githubusersbrowser

import android.app.Application
import com.githubusersbrowser.core.di.dispatchersModule
import com.githubusersbrowser.core.di.localModule
import com.githubusersbrowser.di.activityViewModelModule
import com.githubusersbrowser.user_data.di.dataModule
import com.githubusersbrowser.user_ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(
                dispatchersModule +
                        localModule +
                        dataModule +
                        viewModelModule +
                        activityViewModelModule
            )
        }
    }
}