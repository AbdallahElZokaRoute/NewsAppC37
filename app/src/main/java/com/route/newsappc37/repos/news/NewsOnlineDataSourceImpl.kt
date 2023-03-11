package com.route.newsappc37.repos.news

import com.route.newsappc37.Constants
import com.route.newsappc37.api.WebServices
import com.route.newsappc37.model.ArticlesItem
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : NewsOnlineDataSource {

    override suspend fun getNews(source: String): List<ArticlesItem> {
        try {
            val response = webServices.getNewsBySource(Constants.API_KEY, source, "en")
            return response.articles ?: emptyList()
        } catch (ex: Exception) {
            throw ex
        }
    }
}