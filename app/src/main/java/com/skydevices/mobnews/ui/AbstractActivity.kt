package com.skydevices.mobnews.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class AbstractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout().root)
        onInject()

    }

    protected abstract fun getLayout() : ViewBinding

    protected abstract fun onInject()

}