package com.route.newsappc37.repos.news

import com.route.newsappc37.model.ArticlesItem
import javax.inject.Inject

/**
 * News Repository -> DataSources -> Online Or Offline
 *
 */
class NewsRepositoryImpl @Inject constructor(
    val newsOnlineDataSource: NewsOnlineDataSource,
) : NewsRepository {
    override suspend fun getNews(source: String): List<ArticlesItem> {
        try {
            val response = newsOnlineDataSource.getNews(source)
            return response
        } catch (ex: Exception) {
            throw ex
        }

    }
}