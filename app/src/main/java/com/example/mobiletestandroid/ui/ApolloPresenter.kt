package com.example.mobiletestandroid.ui

import com.example.mobiletestandroid.data.ResponseApolloData
import com.example.mobiletestandroid.domain.ApolloEntity
import com.example.mobiletestandroid.usecases.GetApolloData
import com.example.mobiletestandroid.usecases.UpdateApolloData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApolloPresenter(
    private val getApolloData: GetApolloData,
    private val updateApolloItem: UpdateApolloData
) {

    private var view: View? = null

    interface View {
        fun renderApolloData(apolloData: MutableList<ApolloEntity>)
        fun failGetApolloData()
        fun withoutInternet()
        fun updateFavourite(apollo: ApolloEntity)
        fun navigateToDetail(apollo: ApolloEntity)
    }

    fun onResume() = GlobalScope.launch(Dispatchers.Main) {
        val (apolloData, response) = withContext(Dispatchers.IO) { getApolloData() }

        when (response) {
            ResponseApolloData.SUCCESS -> view?.renderApolloData(apolloData)
            ResponseApolloData.FAILURE -> view?.failGetApolloData()
            ResponseApolloData.WITHOUT_INTERNET -> view?.withoutInternet()
        }
    }

    fun setView(view: View){
        this.view = view
    }

    fun apolloFilter(apolloArray: MutableList<ApolloEntity>, textSearch: String): MutableList<ApolloEntity>{

        val searchLowerCase = textSearch.replace(" ", "").toLowerCase()
        val resultFilter = apolloArray.filter { index ->
            val keywordLowerCase = index.keyWords.replace(" ", "").toLowerCase()
            keywordLowerCase.contains(searchLowerCase)
        }

        return resultFilter.toMutableList()
    }

    fun updateFavourite(apollo: ApolloEntity)
            = GlobalScope.launch(Dispatchers.Main) {

         withContext(Dispatchers.IO) { updateApolloItem(apollo) }

    }

    fun onDestroy() {
        view = null
    }


}