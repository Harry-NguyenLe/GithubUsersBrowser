package com.tymex.interview.user_ui.navigation.graph

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenRouter(
    isLoggedIn: Boolean,
    navController: NavHostController = rememberNavController(),
    exitApp: () -> Unit
) {
    Scaffold {
        HomeNavGraph(
            isLoggedIn = isLoggedIn,
            navController = navController,
            exitApp = exitApp,
        )
    }
}