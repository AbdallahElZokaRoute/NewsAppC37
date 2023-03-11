package com.route.newsappc37.repos.sources

import com.route.newsappc37.model.Category
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.repos.NetworkHandler
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem> {
        if (networkHandler.isOnline()) {
            try {
                val sources = sourcesOnlineDataSource.getSources(category)
                sourcesOfflineDataSource.saveSources(sources)
                return sources
            } catch (ex: Exception) {
                throw ex
            }
        } else {
            try {
                val sources = sourcesOfflineDataSource.getSources(category)
                return sources
            } catch (ex: Exception) {
                throw ex
            }
        }

    }
}