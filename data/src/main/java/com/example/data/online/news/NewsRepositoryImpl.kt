package com.example.data.online.news

import com.example.domain.entities.NetworkResponse
import com.example.domain.entities.NewsItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    override suspend fun getNews(source : String): Flow<NetworkResponse<List<NewsItemDTO>>> {

        return newsOnlineDataSource.getNews(source)

    }
}