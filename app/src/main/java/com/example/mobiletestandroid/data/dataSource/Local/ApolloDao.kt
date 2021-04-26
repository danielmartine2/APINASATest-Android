package com.example.mobiletestandroid.data.dataSource.Local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobiletestandroid.domain.ApolloEntity

@Dao
interface ApolloDao {
    @Query("SELECT * FROM ApolloEntity")
    fun getAll(): MutableList<ApolloEntity>

    @Update
    fun update(apolloEntity: ApolloEntity)

    @Insert
    fun insertAll(apolloEntity: MutableList<ApolloEntity>)

    @Insert
    fun insertOne(apolloEntity: ApolloEntity)


}