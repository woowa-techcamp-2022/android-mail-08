package com.example.mailappproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.mailappproject.R
import com.example.mailappproject.data.User
import com.example.mailappproject.databinding.ActivityMainBinding
import com.example.mailappproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    companion object {
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
        initAppBar()
        initBottomNav()
    }

    private fun initBottomNav() {
        binding.mainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_mail -> {

                }
                R.id.bottom_nav_setting -> {

                }
            }
            true
        }
    }

    private fun initAppBar() {
        binding.run {
            setSupportActionBar(mainToolBar)
            supportActionBar?.title = this@MainActivity.getString(R.string.w_mail)
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                mainDrawerLayout,
                mainToolBar,
                R.string.navigation_open,
                R.string.navigation_close
            )
            mainDrawerLayout.addDrawerListener(toggle)
            mainNavigationView.setCheckedItem(mainViewModel.checkedNavMenuItem.value!!)
            mainNavigationView.setNavigationItemSelectedListener {
                mainViewModel.checkedNavMenuItem.value = it.itemId
                mainDrawerLayout.closeDrawer(GravityCompat.START)
                true
            }
            toggle.syncState()
        }

    }

    private fun initUser() {
        mainViewModel.userLiveData.value = intent.getSerializableExtra("user") as User
        Log.d(TAG, "initUser: ${mainViewModel.userLiveData.value}")
    }
}