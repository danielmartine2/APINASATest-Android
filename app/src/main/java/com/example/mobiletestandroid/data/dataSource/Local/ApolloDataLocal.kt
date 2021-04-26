package com.example.mobiletestandroid.data.dataSource.Local

import android.util.Log
import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.ApolloPersistenceSource
import com.example.mobiletestandroid.domain.ApolloEntity
import com.example.mobiletestandroid.ui.App

class ApolloDataLocal(): ApolloPersistenceSource {


    val app = Util.instance.applicationContext as App

    override fun getApolloData(): MutableList<ApolloEntity> {
        return mutableListOf()
    }

    override fun apolloSaveListData(listData: MutableList<ApolloEntity>) {
    }
}