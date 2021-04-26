package com.example.mobiletestandroid.ui.di

import com.example.mobiletestandroid.ui.ApolloDetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApolloDetailModule::class])
interface ApolloDetailComponent  {
    fun inject(apolloDetailActivity: ApolloDetailActivity)
}