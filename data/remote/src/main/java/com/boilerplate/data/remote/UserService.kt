package com.boilerplate.data.remote

import com.boilerplate.data.model.data.User
import com.boilerplate.data.model.response.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    suspend fun fetchTopUsers(@Query("q") query: String = "PhilippeB",
                           @Query("sort") sort: String = "followers"): ApiResult<User>

    @GET("users/{detail}")
    suspend fun fetchUserDetails(@Path("detail") detail: String): User
}