package com.example.mailappproject.presentation

import android.content.res.Configuration
import android.media.VolumeShaper
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.window.layout.WindowMetrics
import androidx.window.layout.WindowMetricsCalculator
import com.example.mailappproject.R
import com.example.mailappproject.data.User
import com.example.mailappproject.databinding.ActivityMainBinding
import com.example.mailappproject.utils.ConvertUtils
import com.example.mailappproject.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigationrail.NavigationRailView

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

        checkConfiguration()
        initUser()
        initAppBar()
    }


    private fun checkConfiguration(){
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            initNavigation(binding.mainNavigationRailView)
        }else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            initNavigation(binding.mainBottomNavigation)
        }
    }

    private fun <T> initNavigation(t: T){
        mainViewModel.selectedBottomMenuId.value?.let { id ->
            if (t==null) return
            val view = t as NavigationBarView
            view.selectedItemId = id
            view.setOnItemSelectedListener {
                val curId = it.itemId
                mainViewModel.selectedBottomMenuId.value = curId
                beginTransaction(curId, ConvertUtils.getStringById(curId))
                true
            }
            beginTransaction(id, ConvertUtils.getStringById(id))
        }
    }

    private fun beginTransaction(id: Int, tag: String) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if(fragment == null){
            when(id){
                R.id.bottom_nav_mail -> fragment = MailFragment()
                R.id.bottom_nav_setting -> fragment = SettingFragment()
            }
        }else{
            when(id){
                R.id.bottom_nav_mail -> fragment = fragment as MailFragment
                R.id.bottom_nav_setting -> fragment = fragment as SettingFragment
            }
        }
        supportFragmentManager.run {
            val transaction = beginTransaction().replace(R.id.main_fragment_container_view, fragment!!, tag)
            if (backStackEntryCount<1 && id == R.id.bottom_nav_setting){
                transaction.addToBackStack(tag)
            }else if (backStackEntryCount == 1 && id == R.id.bottom_nav_setting){
                return@run
            } else{
                popBackStack()
            }
            transaction.commit()
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
                binding.mainBottomNavigation?.selectedItemId = R.id.bottom_nav_mail
                binding.mainNavigationRailView?.selectedItemId = R.id.bottom_nav_mail
                beginTransaction(R.id.bottom_nav_mail, ConvertUtils.getStringById(R.id.bottom_nav_mail))
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount==1){
            supportFragmentManager.popBackStack()
            if (resources.configuration.orientation==Configuration.ORIENTATION_PORTRAIT)
                binding.mainBottomNavigation?.selectedItemId = R.id.bottom_nav_mail
            else
                binding.mainNavigationRailView?.selectedItemId = R.id.bottom_nav_mail
            mainViewModel.selectedBottomMenuId.value = R.id.bottom_nav_mail
            return
        }
        mainViewModel.run {
            if (selectedBottomMenuId.value == R.id.bottom_nav_mail && checkedNavMenuItem.value!=R.id.nav_primary){
                checkedNavMenuItem.value = R.id.nav_primary
                binding.mainNavigationView.setCheckedItem(R.id.nav_primary)
                return
            }
        }
        super.onBackPressed()

    }
}