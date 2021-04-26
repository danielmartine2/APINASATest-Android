package com.example.mobiletestandroid

import android.content.Context

class Util {
    lateinit var applicationContext: Context
    fun setContext(context: Context) {
        applicationContext = context
    }

    fun getContext(): Context? {
        return applicationContext
    }

    companion object {
        val instance = Util()
    }
}