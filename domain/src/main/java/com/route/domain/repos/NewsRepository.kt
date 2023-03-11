package com.route.domain.repos

import com.route.domain.entity.NewsItemDTO

interface NewsRepository {
    suspend fun getNews(source: String): List<NewsItemDTO>
}

interface NewsOnlineDataSource {
    suspend fun getNews(source: String): List<NewsItemDTO>
}