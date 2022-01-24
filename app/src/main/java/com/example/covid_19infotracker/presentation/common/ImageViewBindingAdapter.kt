package com.example.covid_19infotracker.presentation.common

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.covid_19infotracker.R

@BindingAdapter("app:setImageByUrl")
fun AppCompatImageView.setImageByUrl(imageUrl: String){
    Glide.with(this.context)
        .load(imageUrl)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_launcher_background)
        .into(this)
}