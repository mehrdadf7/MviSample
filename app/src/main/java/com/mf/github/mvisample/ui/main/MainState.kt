package com.mf.github.mvisample.ui.main

import com.mf.github.mvisample.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val users: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}
