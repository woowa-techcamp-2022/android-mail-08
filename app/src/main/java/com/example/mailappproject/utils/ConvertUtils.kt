package com.example.mailappproject.utils

import com.example.mailappproject.R
import java.util.*

object ConvertUtils {
    fun getStringById(id: Int): String = when(id){
        R.id.nav_primary -> "Primary"
        R.id.nav_social -> "Social"
        R.id.nav_promotion -> "Promotion"
        R.id.bottom_nav_mail -> "MailFragment"
        R.id.bottom_nav_setting -> "SettingFragment"
        else -> ""
    }

    fun getRandomWord(length: Int): String {
        val random = Random()
        val stringBuilder = StringBuilder(length)
        repeat(length) {
            val c = 'A' + random.nextInt('k' - 'A')
            if (c.isLetter()) stringBuilder.append(c)
        }
        if (stringBuilder.isEmpty()) stringBuilder.append("하하하")
        return stringBuilder.toString()
    }
}