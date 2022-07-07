package com.example.mailappproject.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.window.layout.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator
import com.example.mailappproject.R
import com.example.mailappproject.data.User
import com.example.mailappproject.databinding.ActivityLoginBinding
import com.example.mailappproject.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val loginViewModel: LoginViewModel by viewModels()
    private val engNumberRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,12}\$".toRegex()
    private val emailRegex = android.util.Patterns.EMAIL_ADDRESS.toRegex()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: resources.displayMetrics.density: ${resources.displayMetrics.density}")
        Log.d(TAG, "onCreate: resources.displayMetrics.density: ${resources.displayMetrics.densityDpi}")
        setContentView(binding.root)

        initLayout()

    }


    private fun initLayout() {
        binding.run {
            loginNicknameEditText.doAfterTextChanged {
                isValidInput(
                    engNumberRegex,
                    it.toString(),
                    R.string.nickname_error_msg,
                    loginNicknameTextInputLayout,
                    loginViewModel.nickNameValidFlag
                )
            }
            loginEmailEditText.doAfterTextChanged {
                isValidInput(
                    emailRegex,
                    it.toString(),
                    R.string.email_error_msg,
                    loginEmailTextInputLayout,
                    loginViewModel.emailValidFlag
                )
            }
            nextBtn.setOnClickListener {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                    putExtra("user", User(
                        nickName = loginNicknameEditText.text.toString(),
                        email = loginEmailEditText.text.toString()
                    ))
                })
                finish()
            }

            loginViewModel.isNextBtnValid.observe(this@LoginActivity){ flag ->
                nextBtn.isEnabled = flag
            }
        }
    }

    private fun isValidInput(
        regex: Regex,
        input: String,
        msgId: Int,
        layout: TextInputLayout,
        liveData: MutableLiveData<Boolean>
    ) {
        regex.matches(input).let { result ->
            liveData.value = result
            layout.error = if (!result) this@LoginActivity.getString(msgId)
            else null
        }
    }
}