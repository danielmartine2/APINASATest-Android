package com.example.mobiletestandroid.data

import android.util.Log
import com.example.mobiletestandroid.domain.ApolloEntity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ApolloMapper {
    fun transformApollo(apolloJson: JSONObject): ApolloEntity?{
        try {
            val data =  apolloJson.getJSONArray("data").getJSONObject(0)
            return ApolloEntity(
                UUID.randomUUID().toString(),
                data.getString("title"),
                if (apolloJson.has("links")) {
                    val link = apolloJson.getJSONArray("links").getJSONObject(0)
                    if (link.has("href")){
                        link.getString("href").replace(" ", "%20")
                    }else{
                        ""
                    }
                }else{
                    ""
                },
                false,
                data.getJSONArray("keywords").toString().replace(",","")
            )
        }catch (e: JSONException){
            Log.d(
                "jsonExceptionTA",
                e.message ?: ""
            )
            return null
        }
    }

    fun transform(apolloJson: JSONObject): MutableList<ApolloEntity>{
        val apolloList: MutableList<ApolloEntity> = mutableListOf()
        try {
            val arrayJsonApollo = apolloJson.getJSONObject("collection").getJSONArray("items")
            for (i in 0 until arrayJsonApollo.length()){
                transformApollo(arrayJsonApollo.getJSONObject(i))?.let {
                    apolloList.add(it)
                }
            }
        }catch (e: JSONException){
            Log.d(
                "jsonExceptionT",
                e.message ?: ""
            )
        }
        return apolloList
    }


    fun transformKeywords(jsonKeywords: JSONArray): MutableList<String>{
        val keywords = mutableListOf<String>()
        for (i in 0 until jsonKeywords.length()){
            keywords.add(jsonKeywords.getString(i))
        }
        return  keywords
    }
}