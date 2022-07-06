package com.example.mailappproject.viewmodel

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mailappproject.R
import com.example.mailappproject.data.User

class MainViewModel:ViewModel() {

    val userLiveData = MutableLiveData<User>()
    val checkedNavMenuItem = MutableLiveData<Int>(R.id.nav_primary)


}