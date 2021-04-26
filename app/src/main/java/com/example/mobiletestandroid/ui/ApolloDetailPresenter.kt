package com.example.mobiletestandroid.ui

import com.example.mobiletestandroid.data.ResponseApolloData
import com.example.mobiletestandroid.domain.ApolloEntity
import com.example.mobiletestandroid.usecases.GetApolloData
import com.example.mobiletestandroid.usecases.UpdateApolloData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApolloDetailPresenter(private val updateApolloItem: UpdateApolloData) {

    fun updateFavourite(apollo: ApolloEntity)
            = GlobalScope.launch(Dispatchers.Main) {

        withContext(Dispatchers.IO) { updateApolloItem(apollo) }

    }


}