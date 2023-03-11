package com.route.data.online.sources


import com.route.domain.entity.SourcesItemDTO
import com.route.domain.repos.SourcesOfflineDataSource
import com.route.data.database.NewsDatabase
import com.route.data.model.SourcesItem
import com.route.data.toDomainObject
import javax.inject.Inject

class SourcesOfflineDataSourceImpl @Inject constructor(
    val newsDatabase: NewsDatabase
) : SourcesOfflineDataSource {
    override suspend fun getSources(category: String): List<SourcesItemDTO> {
        try {
            val response = newsDatabase.getNewsDao().getSourcesByCategory(category)
            return response.map { it.toDomainObject(SourcesItemDTO::class.java) }
        } catch (ex: Exception) {
            throw ex

        }

    }

    override suspend fun saveSources(sources: List<SourcesItemDTO>) {
        try {
            newsDatabase.getNewsDao()
                .saveSources(sources = sources.map { it.toDomainObject(SourcesItem::class.java) })
        } catch (ex: Exception) {
            throw ex

        }
    }


}