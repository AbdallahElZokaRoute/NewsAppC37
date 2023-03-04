package com.route.newsappc37.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsappc37.model.SourcesItem

@Database(entities = [SourcesItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): SourcesDao

    companion object {
        var database: NewsDatabase? = null
        const val DATA_BASE_NAME = "NewsDataBase"
        fun getInstance(context: Context): NewsDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context, NewsDatabase::class.java, DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!


        }
    }

}