package com.route.newsappc37.repos.sources

import com.route.newsappc37.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources(category: String): List<SourcesItem>
}

interface SourcesOnlineDataSource {
    suspend fun getSources(category: String): List<SourcesItem>
}

interface SourcesOfflineDataSource {
    suspend fun getSources(category: String): List<SourcesItem>
    suspend fun saveSources(sources: List<SourcesItem>)
}