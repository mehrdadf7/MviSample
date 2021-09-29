package com.mf.github.mvisample.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mf.github.mvisample.data.api.ApiHelper
import com.mf.github.mvisample.data.repository.MainRepository
import com.mf.github.mvisample.ui.main.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("unknown class name")
    }
}