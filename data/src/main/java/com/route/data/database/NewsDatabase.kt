package com.route.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.data.model.SourcesItem


@Database(entities = [SourcesItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): SourcesDao


}