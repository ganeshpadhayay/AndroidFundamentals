package com.ganesh.androidfundamentals.samplemvvmproject.api

import com.ganesh.androidfundamentals.samplemvvmproject.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //this will be a suspend function as we will call it from a coroutine
    @GET("placeholder/user/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

}