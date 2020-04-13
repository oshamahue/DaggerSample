package com.oshamahue.api

import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("/")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    )
}