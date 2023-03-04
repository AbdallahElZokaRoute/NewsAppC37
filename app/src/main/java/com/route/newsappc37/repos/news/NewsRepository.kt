package com.route.newsappc37.repos.news

import com.route.newsappc37.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(source: String): List<ArticlesItem>
}

interface NewsOnlineDataSource {
    suspend fun getNews(source: String): List<ArticlesItem>
}
