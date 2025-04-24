package com.tymex.interview.user_data.remote

import com.tymex.interview.user_data.remote.response.UserDetailResponse
import com.tymex.interview.user_data.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int?,
        @Query("per_page") perPage: Int
    ): List<UserResponse>

    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<UserDetailResponse>
}