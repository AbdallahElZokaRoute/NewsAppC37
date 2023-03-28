package com.example.data.online.sources



import com.example.domain.entities.SourcesItemDTO
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource
import com.example.domain.repos.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource:  SourcesOfflineDataSource,
    val networkHandler: NetworkHandler
) :  SourcesRepository {
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