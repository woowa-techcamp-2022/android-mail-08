package com.example.mailappproject.data

import android.graphics.Color
import com.example.mailappproject.R
import com.example.mailappproject.utils.ConvertUtils
import kotlin.collections.ArrayList
import java.util.Random


data class Email(
    val source: String,
    val title: String,
    val body: String,
    val date: String,
    val color: Int
) {
    companion object {
        private val colors = arrayOf(
            R.drawable.email_color_0,
            R.drawable.email_color_1,
            R.drawable.email_color_2,
            R.drawable.email_color_3,
            R.drawable.email_color_4,
            R.drawable.email_color_5,
            R.drawable.email_color_6,
        )
        private val random = Random()
        fun getDummy(): List<Email> {
            return ArrayList<Email>().apply {
                repeat(20) {
                    add(
                        Email(
                            ConvertUtils.getRandomWord(random.nextInt(10) + 1),
                            ConvertUtils.getRandomWord(random.nextInt(10) + 1),
                            ConvertUtils.getRandomWord(random.nextInt(10) + 1),
                            ConvertUtils.getRandomWord(random.nextInt(10) + 1),
                            colors[random.nextInt(7)]
                        )
                    )
                }
                add(
                    Email(
                        "조대현",
                        "배고파",
                        "나는 배가 고프다, 회 먹고 싶다 ㅠ",
                        "2023.02.23",
                        colors[random.nextInt(7)]
                    )
                )
            }.sortedBy { it.title }
        }
    }

}
