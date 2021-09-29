package com.mf.github.mvisample.data.repository

import com.mf.github.mvisample.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}