package com.example.mobiletestandroid.data.dataSource.Local

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobiletestandroid.domain.ApolloEntity

@Database(
    entities = [ApolloEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApolloDB: RoomDatabase() {

    abstract  fun apolloDao(): ApolloDao

    companion object{


        fun getDatabase(application: Application): ApolloDB{
            return Room.databaseBuilder(application, ApolloDB::class.java, "apollodb").build()
        }

    }
}