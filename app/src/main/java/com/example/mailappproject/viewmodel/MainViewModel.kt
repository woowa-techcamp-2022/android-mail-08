package com.example.mailappproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mailappproject.data.User

class MainViewModel:ViewModel() {

    val userLiveData = MutableLiveData<User>()

}