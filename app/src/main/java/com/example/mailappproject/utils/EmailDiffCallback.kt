package com.example.mailappproject.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.mailappproject.data.Email

object EmailDiffCallback: DiffUtil.ItemCallback<Email>() {

    override fun areItemsTheSame(oldItem: Email, newItem: Email): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Email, newItem: Email): Boolean {
        return (oldItem.title == newItem.title)
                && (oldItem.body == newItem.body)
                && (oldItem.source == newItem.source)
                && (oldItem.date == newItem.date)
                && (oldItem.color == newItem.color)
    }
}