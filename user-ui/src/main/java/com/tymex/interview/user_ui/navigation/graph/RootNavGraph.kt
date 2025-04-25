package com.tymex.interview.user_ui.navigation.graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.tymex.interview.core.utils.AppAction
import com.tymex.interview.core.utils.SCREEN_TRANSITION_DURATION_IN_MIL
import com.tymex.interview.user_ui.navigation.AUTH_GRAPH_ROUTE
import com.tymex.interview.user_ui.navigation.HOME_GRAPH_ROUTE
import com.tymex.interview.user_ui.navigation.MAIN_GRAPH_ROUTE


@Composable
fun RootNavGraph(
    isLoggedIn: Boolean,
    exitApp: () -> Unit,
    navController: NavHostController,
) {
    val actions = remember(navController) { AppAction(navController) }
    NavHost(
        navController = navController,
        route = HOME_GRAPH_ROUTE,
        startDestination = if (isLoggedIn) {
            MAIN_GRAPH_ROUTE
        } else {
            AUTH_GRAPH_ROUTE
        },
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(SCREEN_TRANSITION_DURATION_IN_MIL),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(SCREEN_TRANSITION_DURATION_IN_MIL),
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(SCREEN_TRANSITION_DURATION_IN_MIL),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(SCREEN_TRANSITION_DURATION_IN_MIL),
            )
        }
    ) {

        authNavGraph(
            onLoginSuccess = {
                navController.navigate(MAIN_GRAPH_ROUTE) {
                    popUpTo(AUTH_GRAPH_ROUTE) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )

        mainNavGraph(
            action = actions,
            exitApp = exitApp
        )
    }
}