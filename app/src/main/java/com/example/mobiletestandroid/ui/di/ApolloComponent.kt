package com.example.mobiletestandroid.ui.di

import com.example.mobiletestandroid.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApolloModule::class])
interface ApolloComponent {
    fun inject(mainActivity: MainActivity)
}