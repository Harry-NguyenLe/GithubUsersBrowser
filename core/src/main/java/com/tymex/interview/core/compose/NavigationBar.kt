package com.tymex.interview.core.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(
    onBackClick: () -> Unit,
    @DrawableRes navigationIcon: Int,
    textTitle: String
) {
    TopAppBar(
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        onBackClick.invoke()
                    },
                painter = painterResource(id = navigationIcon),
                contentDescription = "Appbar back icon"
            )
        },
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = textTitle)
            }
        })
}
