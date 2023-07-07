package com.skydevices.mobnews.util

import android.app.Activity

class Constants {
    companion object{
        const val API_KEY = "044e423c80394a12a41095ab2f6a4979"
        const val BASE_URL = "https://newsapi.org"
        const val SEARCH_NEW_DELAY = 500L

        fun finishWithFadeTransition(activity: Activity) {
            activity.finish()
           activity. overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }


}