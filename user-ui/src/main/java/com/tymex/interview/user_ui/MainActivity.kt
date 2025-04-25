package com.tymex.interview.user_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tymex.interview.user_ui.navigation.graph.RootNavGraph
import com.tymex.interview.user_ui.theme.GithubUsersBrowserTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private var isLoggedIn: Boolean = false


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            mainViewModel.isLoggedIn.collect { loggedIn ->
                isLoggedIn = loggedIn
            }
        }

        setContent {
            GithubUsersBrowserTheme {
                RootNavGraph(
                    isLoggedIn = isLoggedIn,
                    exitApp = {
                        finish()
                    },
                    navController = rememberAnimatedNavController(),
                )
            }
        }
    }
}
