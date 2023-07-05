package com.skydevices.mobnews.ui

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.skydevices.mobnews.databinding.ActivitySplashBinding

class SplashActivity : AbstractActivity() {
    private lateinit var binding: ActivitySplashBinding


    override fun getLayout(): ViewBinding {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


       /* Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // Tempo de exibição da splash screen em milissegundos*/

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()

            }, 3000)
    }

}


