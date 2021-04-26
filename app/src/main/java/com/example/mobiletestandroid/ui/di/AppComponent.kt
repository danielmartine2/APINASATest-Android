package com.example.mobiletestandroid.ui.di

import com.example.mobiletestandroid.ui.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
}