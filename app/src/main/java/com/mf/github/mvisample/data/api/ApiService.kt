package com.mf.github.mvisample.data.api

import com.mf.github.mvisample.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers() : List<User>

}