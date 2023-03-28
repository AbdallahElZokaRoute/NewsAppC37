package com.route.newsappc37.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.SourcesItem
import com.example.domain.entities.SourcesItemDTO


@Database(entities = [SourcesItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getNewsDao() : SourcesDao


    }






