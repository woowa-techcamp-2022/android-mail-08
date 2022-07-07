package com.example.mailappproject.viewmodel

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mailappproject.R
import com.example.mailappproject.data.Email
import com.example.mailappproject.data.User
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainViewModel : ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }

    val userLiveData = MutableLiveData<User>()
    val checkedNavMenuItem = MutableLiveData<Int>(R.id.nav_primary)
    val selectedBottomMenuId = MutableLiveData<Int>(R.id.bottom_nav_mail)
    private var _dummyEmailData = MutableLiveData<HashMap<Int, List<Email>>>()
    val dummyEmailData: LiveData<HashMap<Int, List<Email>>> get() = _dummyEmailData

    init {
        _dummyEmailData.value = HashMap()
        _dummyEmailData.value?.set(R.id.nav_primary, Email.getDummy())
        _dummyEmailData.value?.set(R.id.nav_social, Email.getDummy())
        _dummyEmailData.value?.set(R.id.nav_promotion, Email.getDummy())
        dummyEmailData.value!!.forEach {
            Log.d(TAG, ": ${it}")
        }
    }





}