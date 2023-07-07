package com.skydevices.mobnews.ui

import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.databinding.ActivityFavoriteBinding
import com.skydevices.mobnews.util.Constants

class FavoriteActivity : AbstractActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        supportActionBar?.title = "Favoritos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        Constants.finishWithFadeTransition(this)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Constants.finishWithFadeTransition(this)
    }

}