package com.tymex.interview.user_ui.navigation.graph

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tymex.interview.core.utils.AppAction
import com.tymex.interview.user_ui.navigation.GithubUiScreen
import com.tymex.interview.user_ui.navigation.MAIN_GRAPH_ROUTE
import com.tymex.interview.user_ui.screen.detail.GithubUserDetailUiContent
import com.tymex.interview.user_ui.screen.list.GithubUsersUiContent
import timber.log.Timber

fun NavGraphBuilder.mainNavGraph(
    action: AppAction,
    exitApp: () -> Unit
) {
    navigation(
        startDestination = GithubUiScreen.ListUserScreen.route,
        route = MAIN_GRAPH_ROUTE
    ) {

        composable(
            route = GithubUiScreen.ListUserScreen.route,
        ) {
            BackHandler {
                exitApp.invoke()
            }

            GithubUsersUiContent(
                onBackClick = {
                    exitApp.invoke()
                },
                onNavigateToDetail = { username ->
                    action.navigateToRoute(
                        route = GithubUiScreen.DetailScreen.route(username),
                        from = it
                    )
                },
            )
        }

        composable(
            route = GithubUiScreen.DetailScreen.routeTemplate
        ) { backStackEntry ->

            BackHandler {
                action.popBackStack()
            }

            val username = backStackEntry.arguments?.getString(GithubUiScreen.DetailScreen.ARG_USERNAME)

            Timber.d("username from nav args: $username")

            GithubUserDetailUiContent(
                userName = username.orEmpty()
            ) {
                action.popBackStack()
            }
        }
    }
}