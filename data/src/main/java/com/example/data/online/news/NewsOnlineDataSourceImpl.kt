package com.example.data.online.news

import com.example.data.model.NewsItem
import com.example.data.online.Constants
import com.example.data.online.WebServices
import com.example.data.toDomainobject
import com.example.domain.entities.NewsItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(val webServices : WebServices) : NewsOnlineDataSource {
    override suspend fun getNews(source: String): List<NewsItemDTO> {
        try {



            val response = webServices.getNewsBySource(Constants.API_KEY,source)
            return response.articles?.map {
                it.toDomainobject(NewsItemDTO::class.java)
            } ?: emptyList() }
        catch (ex : Exception){

            throw ex
        }
    }
}