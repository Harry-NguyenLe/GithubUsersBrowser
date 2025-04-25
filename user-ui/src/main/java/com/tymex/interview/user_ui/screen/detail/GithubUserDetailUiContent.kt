package com.tymex.interview.user_ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tymex.interview.core.compose.ErrorMessage
import com.tymex.interview.core.compose.LoadingIndicator
import com.tymex.interview.core.compose.NavigationBar
import com.tymex.interview.core.compose.UserInfoCard
import com.tymex.interview.user_ui.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun GithubUserDetailUiContent(
    userName: String,
    viewModel: GithubUserDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getUserDetail(userName)
    }

    Scaffold(
        topBar = {
            NavigationBar(
                onBackClick = onBackClick,
                navigationIcon = R.drawable.baseline_arrow_back_ios_new_24,
                textTitle = "User Details"
            )
        }
    ) { paddingValues ->

        when {
            uiState.isLoading -> {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            uiState.exception != null -> {
                val errorMessage = uiState.exception?.message ?: "Unknown error"
                ErrorMessage(
                    modifier = Modifier.fillMaxSize(),
                    message = errorMessage,
                    onRetry = { viewModel.getUserDetail(userName) }
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    UserInfoCard(
                        modifier = Modifier
                            .padding(paddingValues),
                        avatarUrl = uiState.avatarUrl,
                        name = uiState.userName,
                    ) {
                        NameAndLocationContent(
                            fullName = uiState.userName,
                            location = uiState.location,
                        )
                    }

                    // Section 2: Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem(
                            icon = R.drawable.baseline_people_24,
                            count = uiState.followers,
                            label = "Follower"
                        )
                        StatItem(
                            icon = R.drawable.baseline_cloud_circle_24,
                            count = uiState.following,
                            label = "Following"
                        )
                    }

                    // Section 3: Blog Info
                    if (uiState.blog.isNotBlank()) {
                        Column {
                            Text(
                                text = "Blog",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = uiState.blog,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary // Link-like color
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NameAndLocationContent(fullName: String, location: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(16.dp),
                tint = LocalContentColor.current.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun StatItem(
    @DrawableRes icon: Int,
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Circular Icon Background
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer, // Use theme color
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = label, // Accessibility
                tint = MaterialTheme.colorScheme.onSecondaryContainer, // Use theme color
                modifier = Modifier.size(24.dp)
            )
        }
        // Count Text
        Text(
            text = count,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        // Label Text
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium, // Smaller label style
            color = LocalContentColor.current.copy(alpha = 0.7f) // Slightly faded
        )
    }
}