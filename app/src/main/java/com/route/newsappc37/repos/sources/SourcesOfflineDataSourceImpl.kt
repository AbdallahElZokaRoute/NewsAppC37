package com.route.newsappc37.repos.sources

import com.route.newsappc37.database.NewsDatabase
import com.route.newsappc37.model.SourcesItem
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class SourcesOfflineDataSourceImpl @Inject constructor(
    val newsDatabase: NewsDatabase
) : SourcesOfflineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem> {
        try {
            val response = newsDatabase.getNewsDao().getSourcesByCategory(category)
            return response
        } catch (ex: Exception) {
            throw ex

        }

    }

    override suspend fun saveSources(sources: List<SourcesItem>) {
        try {
            newsDatabase.getNewsDao().saveSources(sources = sources)
        } catch (ex: Exception) {
            throw ex

        }
    }


}