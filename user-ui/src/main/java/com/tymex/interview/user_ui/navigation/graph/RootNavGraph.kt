package com.tymex.interview.user_ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tymex.interview.user_ui.navigation.HOME_GRAPH_ROUTE
import com.tymex.interview.user_ui.navigation.ROOT_GRAPH_ROUTE


@Composable
fun RootNavGraph(
    isLoggedIn: Boolean,
    exitApp: () -> Unit,
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = HOME_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE,
    ) {
        composable(
            route = HOME_GRAPH_ROUTE
        ) {
            ScreenRouter(
                isLoggedIn = isLoggedIn,
                exitApp = exitApp,
            )
        }
    }
}