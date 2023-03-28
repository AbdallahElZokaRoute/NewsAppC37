package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.route.newsappc37.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {


        private const val DATABASE_NAME = "News_DataBase"

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context : Context) : NewsDatabase {

        return Room.databaseBuilder(context,NewsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    }


}


