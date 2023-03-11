package com.route.data.online.sources


import com.route.domain.entity.SourcesItemDTO
import com.route.domain.repos.NetworkHandler
import com.route.domain.repos.SourcesOfflineDataSource
import com.route.domain.repos.SourcesOnlineDataSource
import com.route.domain.repos.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItemDTO> {
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