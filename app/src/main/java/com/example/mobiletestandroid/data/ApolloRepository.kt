package com.example.mobiletestandroid.data

import android.app.Application
import android.util.Log
import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.dataSource.Cloud.OnResponseGetApolloData
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDB
import com.example.mobiletestandroid.domain.ApolloEntity
import org.json.JSONObject

class ApolloRepository(
    private val apolloPersistenceSource: ApolloPersistenceSource,
    private val apolloCloudSource: ApolloCloudSource,
    private val apolloMapper: ApolloMapper,
    private val util: Util
) {
    fun getSaveApolloData(): Pair<MutableList<ApolloEntity>, ResponseApolloData> {
        var response = Pair(mutableListOf<ApolloEntity>(), ResponseApolloData.FAILURE)
         apolloCloudSource.getApolloData(object :
             OnResponseGetApolloData {
            override fun onSuccess(apolloData: JSONObject) {
                val apolloList = apolloMapper.transform(apolloData)
                util.getContext()?.let {
                    val data = ApolloDB.getDatabase(it as Application).apolloDao().insertAll(apolloList)
                    Log.i("room", "Apollo $data")
                }
                response = Pair(apolloList, ResponseApolloData.SUCCESS)

            }

            override fun onFailure() {
                response = Pair(mutableListOf<ApolloEntity>(), ResponseApolloData.FAILURE)
            }

            override fun withoutInternet() {
                response = Pair(mutableListOf<ApolloEntity>(), ResponseApolloData.WITHOUT_INTERNET)
            }

        })
        return response
    }



    fun updateApollo(apollo: ApolloEntity){
        util.getContext()?.let {
            val data = ApolloDB.getDatabase(it as Application).apolloDao().update(apollo)
            Log.i("room", "Apollo update $data")
        }
    }

    fun getApolloList(): Pair<MutableList<ApolloEntity>, ResponseApolloData> {

        var apolloList = mutableListOf<ApolloEntity>()
        util.getContext()?.let {
            apolloList = ApolloDB.getDatabase(it as Application).apolloDao().getAll()
            Log.i("room", "Apollo getAll ${apolloList.size}")
        }
        return if (apolloList.isEmpty()){
            this.getSaveApolloData()
        }else{
            Pair(apolloList, ResponseApolloData.SUCCESS)
        }

    }
}

interface ApolloPersistenceSource {
    fun getApolloData(): MutableList<ApolloEntity>
    fun apolloSaveListData(listData: MutableList<ApolloEntity>)
}

interface  ApolloCloudSource {
    fun getApolloData(listen: OnResponseGetApolloData)
}

enum class ResponseApolloData(val response: Int){
    SUCCESS(1),
    FAILURE(2),
    WITHOUT_INTERNET(3)
}