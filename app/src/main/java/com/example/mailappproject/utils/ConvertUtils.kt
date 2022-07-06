package com.example.mailappproject.utils

import com.example.mailappproject.R

object ConvertUtils {
    fun getStringById(id: Int): String = when(id){
        R.id.nav_primary -> "Primary"
        R.id.nav_social -> "Primary"
        R.id.nav_promotion -> "Promotion"
        R.id.bottom_nav_mail -> "MailFragment"
        R.id.bottom_nav_setting -> "SettingFragment"
        else -> ""
    }
}