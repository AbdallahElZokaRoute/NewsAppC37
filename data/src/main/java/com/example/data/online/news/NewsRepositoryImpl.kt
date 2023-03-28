package com.example.data.online.news

import com.example.domain.entities.NewsItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    override suspend fun getNews(source : String): List<NewsItemDTO> {

        try {
            val respone = newsOnlineDataSource.getNews(source)
            return respone
        }
        catch (ex : Exception){

            throw ex

        }

    }
}