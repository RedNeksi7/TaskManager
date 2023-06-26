package com.example.taskmanager.ui.onboading.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    val list = listOf(
        OnBoarding(
            "https://img.geliophoto.com/bishkek/00_bishkek.jpg",
            "Бишкек",
            "Население: 1071261ч."
        ),
        OnBoarding("https://24.kg/files/media/79/79892.jpg", "Чолпон-Ата", "12500ч."),
        OnBoarding(
            "https://skigu.ru/upload/iblock/6d6/6d6d1d387ac358c7e6e18775bd51c241.jpg",
            "Каракол",
            "82000ч."
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) {
            binding.tvDesk.text = onBoarding.desc
            binding.tvTitle.text = onBoarding.title
            Glide.with(binding.ivOnboard).load(onBoarding.image).into(binding.ivOnboard)
            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnStart.setOnClickListener {
                onClick()
            }
        }

    }
}