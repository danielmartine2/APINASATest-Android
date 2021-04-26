package com.example.mobiletestandroid.ui.di

import android.content.Context
import com.example.mobiletestandroid.ui.App
import com.loopj.android.http.AsyncHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val  application : App){


    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideClient() = AsyncHttpClient()


}