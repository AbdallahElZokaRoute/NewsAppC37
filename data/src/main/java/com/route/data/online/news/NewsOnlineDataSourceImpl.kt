package com.route.data.online.news

import com.route.data.online.Constants
import com.route.data.online.WebServices
import com.route.data.toDomainObject
import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsOnlineDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : NewsOnlineDataSource {
    override suspend fun getNews(source: String): List<NewsItemDTO> {
        try {
            val response = webServices.getNewsBySource(Constants.API_KEY, source, "en")
            return response.articles?.map {
                it.toDomainObject(NewsItemDTO::class.java)
            } ?: emptyList()
        } catch (ex: Exception) {
            throw ex
        }
    }


}