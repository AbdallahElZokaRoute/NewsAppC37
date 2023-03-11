package com.route.newsappc37.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsappc37.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): SourcesDao


}