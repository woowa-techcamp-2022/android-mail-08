package com.example.mailappproject.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mailappproject.R
import com.example.mailappproject.databinding.ActivityLoginBinding
import com.example.mailappproject.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}