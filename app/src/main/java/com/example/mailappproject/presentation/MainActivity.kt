package com.example.mailappproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mailappproject.data.User
import com.example.mailappproject.databinding.ActivityMainBinding
import com.example.mailappproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUser()
    }

    private fun initUser() {
        mainViewModel.userLiveData.value = intent.getSerializableExtra("user") as User
        Log.d(TAG, "initUser: ${mainViewModel.userLiveData.value}")
    }
}