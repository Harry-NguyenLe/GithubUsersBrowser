package com.tymex.interview.core.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatchersModule = module {

    /**
     * Provides Dispatchers.IO using a qualifier.
     * Ideal for network requests, disk I/O, database operations, etc.
     */
    single<CoroutineDispatcher>(namedIO()) {
        Dispatchers.IO
    }

    /**
     * Provides Dispatchers.Main using a qualifier.
     * Necessary for interacting with the UI thread (e.g., updating LiveData, UI elements).
     * Note: In unit tests, replace this with a TestCoroutineDispatcher.
     */
    single<CoroutineDispatcher>(namedMain()) {
        Dispatchers.Main
    }

    /**
     * Provides Dispatchers.Default using a qualifier.
     * Suitable for CPU-intensive tasks like sorting large lists, complex calculations, JSON parsing.
     */
    single<CoroutineDispatcher>(namedDefault()) {
        Dispatchers.Default
    }

    /**
     * Provides Dispatchers.Unconfined using a qualifier.
     * Use with caution. It doesn't confine execution to any specific thread pool.
     * Often useful in tests or specific scenarios, but generally avoid for standard app logic.
     */
    single<CoroutineDispatcher>(namedUnconfined()) {
        Dispatchers.Unconfined
    }
}