package com.example.data.online.sources

import com.example.data.model.SourcesItem
import com.example.data.toDomainobject
import com.example.domain.entities.SourcesItemDTO
import com.example.domain.repos.SourcesOfflineDataSource
import com.route.newsappc37.database.NewsDatabase
import javax.inject.Inject

class SourcesOfflineDataSourceImpl @Inject constructor(val newsDatabase: NewsDatabase) :
    SourcesOfflineDataSource {
    override suspend fun getSources( category : String ): List<SourcesItemDTO> {

        try {
            val response = newsDatabase.getNewsDao().getSourcesByCategory(category)
            return response.map {
                it.toDomainobject(
                    SourcesItemDTO::class.java
                )
            }
        }
        catch (ex : Exception){

            throw ex
        }


    }

    override suspend fun saveSources(sources: List<SourcesItemDTO>) {
        newsDatabase.getNewsDao().saveSource(sources.map {
            it.toDomainobject(SourcesItem::class.java)
        })
    }
}