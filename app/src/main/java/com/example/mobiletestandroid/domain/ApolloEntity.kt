package com.example.mobiletestandroid.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ApolloEntity(
    @PrimaryKey val id: String,
    @ColumnInfo val title: String,
    @ColumnInfo val image: String,
    @ColumnInfo var isFavourite: Boolean,
    @ColumnInfo val keyWords: String): Serializable




