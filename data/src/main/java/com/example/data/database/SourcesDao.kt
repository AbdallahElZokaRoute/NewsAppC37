package com.route.newsappc37.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.SourcesItem


@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSources() : List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSource(sources: List<SourcesItem>)
@Query("Select * from SourcesItem Where category = :category ")
    suspend fun getSourcesByCategory(category : String) : List<SourcesItem>
}