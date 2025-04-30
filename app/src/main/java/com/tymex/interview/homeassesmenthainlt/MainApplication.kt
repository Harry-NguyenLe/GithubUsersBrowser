package com.tymex.interview.homeassesmenthainlt

import android.app.Application
import com.tymex.interview.core.di.dispatchersModule
import com.tymex.interview.core.di.localModule
import com.tymex.interview.homeassesmenthainlt.di.activityViewModelModule
import com.tymex.interview.user_data.di.dataModule
import com.tymex.interview.user_ui.di.viewModelModule
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