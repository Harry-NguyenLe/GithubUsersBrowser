package com.tymex.interview.user_ui.dummy

import com.tymex.interview.user_domain.model.User
import com.tymex.interview.user_domain.model.UserDetail

val dummyUser = User(
    login = "user1",
    id = 1,
    avatarUrl = "avatarUrl",
    url = "url",
)

val dummyUserDetail = UserDetail(
    login = "login",
    id = 1,
    name = "Harry",
    avatarUrl = "https://example.com/avatar.png",
    blog = "https://blog.example.com",
    location = "Vietnam",
    followers = 1234,
    following = 5678
)