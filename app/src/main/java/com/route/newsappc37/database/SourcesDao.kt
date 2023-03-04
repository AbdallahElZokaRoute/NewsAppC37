package com.route.newsappc37.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsappc37.model.SourcesItem

@Dao
interface SourcesDao {
    @Query("Select * from SourcesItem")
    suspend fun getSources(): List<SourcesItem>

    @Query("Select * from SourcesItem where category= :category")
    suspend fun getSourcesByCategory(category: String): List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSources(sources: List<SourcesItem>)


}