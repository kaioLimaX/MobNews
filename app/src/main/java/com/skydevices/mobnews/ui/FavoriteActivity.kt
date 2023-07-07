package com.skydevices.mobnews.ui

import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.databinding.ActivityFavoriteBinding

class FavoriteActivity : AbstractActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {

    }

}