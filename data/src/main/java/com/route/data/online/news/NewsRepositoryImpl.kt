package com.route.data.online.news

import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsOnlineDataSource
import com.route.domain.repos.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val newsOnlineDataSource: NewsOnlineDataSource,
) : NewsRepository {
    override suspend fun getNews(source: String): List<NewsItemDTO> {
        try {
            val response = newsOnlineDataSource.getNews(source)
            return response
        } catch (ex: Exception) {
            throw ex
        }

    }
}