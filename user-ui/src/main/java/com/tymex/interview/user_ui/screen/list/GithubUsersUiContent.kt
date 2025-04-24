package com.tymex.interview.user_ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tymex.interview.core.compose.ErrorMessage
import com.tymex.interview.core.compose.LoadingIndicator
import com.tymex.interview.core.compose.NavigationBar
import com.tymex.interview.core.compose.UserInfoCard
import com.tymex.interview.user_ui.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun GithubUsersUiContent(
    onBackClick: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: GithubUsersViewModel = koinViewModel()
) {
    val lazyPagingItems = viewModel.usersFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            NavigationBar(
                onBackClick = onBackClick,
                navigationIcon = R.drawable.baseline_arrow_back_ios_new_24,
                textTitle = "GitHub Users"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .background(color = Color.Transparent)
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey { user -> user.id!! }
            ) { index ->
                val user = lazyPagingItems[index]
                user?.let {
                    UserInfoCard(
                        onClick = {
                            onNavigateToDetail.invoke(it.login.orEmpty())
                        },
                        avatarUrl = it.avatarUrl,
                        name = it.login.orEmpty(),
                        content = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = it.login.orEmpty(),
                                    style = MaterialTheme.typography.titleLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(
                                    modifier = Modifier
                                        .height(2.dp)
                                        .fillMaxWidth()
                                        .background(Color.DarkGray)
                                )
                                Text(
                                    text = it.url.orEmpty(),
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    )
                } ?: Text("Loading item...")
            }

            // Handle loading state
            lazyPagingItems.loadState.apply {
                when {
                    // Initial load or refresh
                    refresh is LoadState.Loading -> {
                        item { LoadingIndicator(modifier = Modifier.fillParentMaxSize()) }
                    }

                    refresh is LoadState.Error -> {
                        val errorMessage = (refresh as LoadState.Error).error.message
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillMaxSize(),
                                message = errorMessage ?: "Unknown error",
                                onRetry = { lazyPagingItems.retry() })
                        }
                    }

                    // Append loading more items
                    append is LoadState.Loading -> {
                        item {
                            LoadingIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }

                    append is LoadState.Error -> {
                        val e = lazyPagingItems.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                message = "Error loading more: ${e.error.localizedMessage}",
                                onRetry = { lazyPagingItems.retry() },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}