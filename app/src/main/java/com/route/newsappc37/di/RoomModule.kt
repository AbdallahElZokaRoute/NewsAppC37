package com.route.newsappc37.di

import android.content.Context
import androidx.room.Room
import com.route.newsappc37.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    const val DATA_BASE_NAME = "NewsDataBase"

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context): NewsDatabase {
        //SingleObject + Global Accessible
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DATA_BASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

}
