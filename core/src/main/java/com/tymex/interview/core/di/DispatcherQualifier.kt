package com.tymex.interview.core.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

enum class DispatcherQualifier {
    IO, Main, Default, Unconfined
}

fun namedIO(): Qualifier = named(DispatcherQualifier.IO)
fun namedMain(): Qualifier = named(DispatcherQualifier.Main)
fun namedDefault(): Qualifier = named(DispatcherQualifier.Default)
fun namedUnconfined(): Qualifier = named(DispatcherQualifier.Unconfined)