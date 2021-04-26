package com.example.mobiletestandroid.data.dataSource.Cloud

import android.util.Log
import com.example.mobiletestandroid.data.ApolloCloudSource
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import org.json.JSONObject

class ApolloDataCloud(private val client: SyncHttpClient, private val endPoints: EndPoints): ApolloCloudSource {

    override fun getApolloData(listen: OnResponseGetApolloData) {
        client.get(endPoints.apollo, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?

            ) {

                val response = responseBody?.let { String(it) }
                if (response.isNullOrBlank()){
                    listen.onFailure()
                }else{
                    response?.let {
                        listen.onSuccess(JSONObject(it))
                    }
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out cz.msebera.android.httpclient.Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                if (statusCode == 0) {
                    listen.withoutInternet()
                }else{
                    listen.onFailure()
                }

                val response = responseBody?.let { String(it) }
                Log.d("getResponseHistory", response.toString())

            }
        })
    }
}

interface OnResponseGetApolloData {
    fun onSuccess(apolloData : JSONObject)
    fun onFailure()
    fun withoutInternet()
}

