package com.example.mailappproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    companion object {
        const val TAG = "LoginViewModel"
    }
    val emailValidFlag = MutableLiveData(false)
    val nickNameValidFlag = MutableLiveData(false)

    private val _isNextBtnValid = MediatorLiveData<Boolean>()
    val isNextBtnValid : LiveData<Boolean> get() = _isNextBtnValid

    init {
        _isNextBtnValid.addSource(emailValidFlag) {
            checkAllValid(emailValidFlag, nickNameValidFlag)
        }
        _isNextBtnValid.addSource(nickNameValidFlag) {
            checkAllValid(emailValidFlag, nickNameValidFlag)
        }
    }

    private fun checkAllValid(emailFlag: LiveData<Boolean>, nickNameFlag: LiveData<Boolean>){
        _isNextBtnValid.value = emailFlag.value!! && nickNameFlag.value!!
    }
}