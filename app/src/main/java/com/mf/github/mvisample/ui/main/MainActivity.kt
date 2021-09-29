package com.mf.github.mvisample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mf.github.mvisample.data.api.ApiHelperImpl
import com.mf.github.mvisample.data.api.RetrofitBuilder
import com.mf.github.mvisample.databinding.ActivityMainBinding
import com.mf.github.mvisample.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val mainAdapter by lazy { return@lazy MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        initViews()
        initObservers()

        binding.fetchUsers.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }

    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        )[MainViewModel::class.java]
    }

    private fun initViews() {
        binding.list.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    (layoutManager as LinearLayoutManager).orientation
                )
            )
            adapter = mainAdapter
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    MainState.Idle -> {
                    }
                    MainState.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.fetchUsers.visibility = View.GONE
                    }
                    is MainState.Users -> {
                        binding.loading.visibility = View.GONE
                        binding.fetchUsers.visibility = View.GONE
                        val users = it.users
                        mainAdapter.submitList(users)
                    }
                    is MainState.Error -> {
                        binding.loading.visibility = View.GONE
                        binding.fetchUsers.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}