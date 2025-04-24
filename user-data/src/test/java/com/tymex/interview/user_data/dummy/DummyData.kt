package com.tymex.interview.user_data.dummy

import com.squareup.moshi.Types
import com.tymex.interview.shared_test.base.provideMoshi
import com.tymex.interview.user_data.remote.response.UserDetailResponse
import com.tymex.interview.user_data.remote.response.UserResponse
import com.tymex.interview.user_domain.model.User
import com.tymex.interview.user_domain.model.UserDetail
import java.io.FileReader

private val moshi = provideMoshi()

val dummyUserResponse = listOf(
    UserResponse(
        id = 1,
        login = "user1",
        avatarUrl = "avatar1"
    ),
    UserResponse(
        id = 2,
        login = "user2",
        avatarUrl = "avatar2"
    )
)

val dummyUserDetailResponse = UserDetailResponse(
    login = "testUser",
    id = 1,
    avatarUrl = "avatarUrl",
    name = "name",
    blog = "blog",
    location = "location",
    followers = 1,
    following = 2
)

val dummyUserDetail = UserDetail(
    login = "testUser",
    id = 1,
    avatarUrl = "avatarUrl",
    name = "name",
    blog = "blog",
    location = "location",
    followers = 1,
    following = 2
)

val dummyUser = User(
    login = "user1",
    id = 1,
    avatarUrl = "avatar1",
    url = "url"
)

fun getUserResponse(): List<UserResponse> {
    val type = Types.newParameterizedType(List::class.java, UserResponse::class.java)
    val adapter = moshi.adapter<List<UserResponse>>(type)
    return adapter.fromJson(readTestFile("get_user_response_200.json"))!!
}

fun getUserDetailResponse(): UserDetailResponse {
    val adapter = moshi.adapter(UserDetailResponse::class.java)
    return adapter.fromJson(readTestFile("get_user_detail_response_200.json"))!!
}

fun readTestFile(fileName: String): String =
    FileReader("src/test/testFiles/$fileName").use { reader -> reader.readText() }