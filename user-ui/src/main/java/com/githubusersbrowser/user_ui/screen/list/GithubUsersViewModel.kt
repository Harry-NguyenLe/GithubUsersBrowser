package com.githubusersbrowser.user_ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.githubusersbrowser.user_domain.model.User
import com.githubusersbrowser.user_domain.usecase.GetUserPagingUseCase
import kotlinx.coroutines.flow.Flow

class GithubUsersViewModel(
    useCase: GetUserPagingUseCase
) : ViewModel() {

    val usersFlow: Flow<PagingData<User>> = useCase.invoke()
        .cachedIn(viewModelScope)
}