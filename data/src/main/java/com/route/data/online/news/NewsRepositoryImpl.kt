package com.route.data.online.news

import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsOnlineDataSource
import com.route.domain.repos.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val newsOnlineDataSource: NewsOnlineDataSource,
) : NewsRepository {
    override suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>> {
        return newsOnlineDataSource.getNews(source)
    }
}
