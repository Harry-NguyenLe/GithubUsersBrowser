package com.tymex.interview.user_ui.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val MAIN_GRAPH_ROUTE = "main"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class GithubUiScreen(
    val route: String,
    val screenTitle: String,
) {

    data object ListUserScreen : GithubUiScreen(
        route = "list_user_screen",
        screenTitle = "Github Users",
    )

    data object DetailScreen {
        const val ARG_USERNAME = "username"
        const val routeTemplate =
            "detail_screen/{$ARG_USERNAME}"

        fun route(username: String): String = "detail_screen/$username"
    }

    data object LoginScreen: GithubUiScreen(
        route = "login_screen",
        screenTitle = "User Login"
    )
}