package com.mf.github.mvisample.data.api

import com.mf.github.mvisample.data.model.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}