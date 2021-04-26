package com.example.mobiletestandroid.ui

import android.app.Application
import androidx.room.Room
import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDB
import com.example.mobiletestandroid.ui.di.AppComponent
import com.example.mobiletestandroid.ui.di.DaggerAppComponent

class App: Application() {

    private  lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()

        Util.instance.setContext(this)
    }
}