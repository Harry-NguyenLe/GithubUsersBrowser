package com.tymex.interview.user_ui.navigation.graph

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tymex.interview.user_ui.navigation.AUTH_GRAPH_ROUTE
import com.tymex.interview.user_ui.navigation.GithubUiScreen
import com.tymex.interview.user_ui.screen.login.AuthUiContent

fun NavGraphBuilder.authNavGraph(
    onLoginSuccess: () -> Unit
) {

    navigation(
        startDestination = GithubUiScreen.LoginScreen.route,
        route = AUTH_GRAPH_ROUTE
    ) {

        composable(
            route = GithubUiScreen.LoginScreen.route,
        ) {
            BackHandler {
                // Disable back press
            }
            AuthUiContent(
                onLoginSuccess = onLoginSuccess
            )
        }
    }
}