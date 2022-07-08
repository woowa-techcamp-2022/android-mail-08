package com.example.mailappproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mailappproject.R
import com.example.mailappproject.data.Email
import com.example.mailappproject.databinding.EmailRowBinding
import com.example.mailappproject.utils.EmailDiffCallback

class EmailAdapter : ListAdapter<Email, EmailAdapter.ViewHolder>(EmailDiffCallback) {
    class ViewHolder(private val binding: EmailRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(email: Email) {
            binding.run {
                emailRowTitle.text = email.title
                emailRowSource.text = email.source
                emailRowBody.text = email.body
                if (email.source.first() in 'a'..'z' || email.source.first() in 'A'..'Z') {
                    Glide.with(binding.root)
                        .load(email.color)
                        .into(binding.emailRowImageView)
                    emailRowFirstName.text = email.source.first().uppercase()
                } else {
                    Glide.with(binding.root)
                        .load(R.drawable.ic_baseline_account_circle_24)
                        .circleCrop()
                        .override(70,70)
                        .into(binding.emailRowImageView)
                    emailRowFirstName.text = ""
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmailRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}