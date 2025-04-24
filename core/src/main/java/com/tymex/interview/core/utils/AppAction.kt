package com.tymex.interview.core.utils

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

class AppAction(private val navController: NavController) {
    private val currentRoute: String?
        get() = navController.currentDestination?.route

    val popBackStack: () -> Unit = {
        DebounceEventHelper.getInstance().debounceEvent {
            navController.popBackStack()
        }
    }

    /**
     * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
     *
     * This is used to de-duplicate navigation events.
     */
    private fun NavBackStackEntry.lifecycleIsResumed() =
        this.lifecycle.currentState == Lifecycle.State.RESUMED

    fun navigateToRoute(
        route: String,
        from: NavBackStackEntry,
        builder: (NavOptionsBuilder.() -> Unit)? = null
    ) {
        if (route != currentRoute && from.lifecycleIsResumed()) {
            builder?.let {
                navController.navigate(route = route, builder = builder)
            } ?: run {
                navController.navigate(route = route)
            }
        }
    }
}