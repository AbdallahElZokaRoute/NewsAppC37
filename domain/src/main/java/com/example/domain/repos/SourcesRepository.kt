package com.example.domain.repos

import com.example.domain.entities.SourcesItemDTO

interface SourcesRepository {
    suspend fun getSources(category: String) : List<SourcesItemDTO>
}
interface SourcesOnlineDataSource{
    suspend fun getSources(category : String) : List<SourcesItemDTO>


}

interface SourcesOfflineDataSource{
    suspend fun getSources(category: String) : List<SourcesItemDTO>
    suspend fun saveSources(sources : List<SourcesItemDTO>)

}