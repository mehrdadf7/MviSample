package com.mf.github.mvisample.data.api

import com.mf.github.mvisample.data.model.User

interface ApiHelper {

    suspend fun getUsers() : List<User>

}