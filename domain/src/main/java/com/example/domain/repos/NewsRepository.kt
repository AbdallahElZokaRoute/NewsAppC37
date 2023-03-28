package com.example.domain.repos

import com.example.domain.entities.NewsItemDTO
import dagger.Binds
import dagger.Provides


interface NewsRepository {

    suspend fun getNews(source: String) : List<NewsItemDTO>

}

interface NewsOnlineDataSource{

    suspend fun getNews(source: String) : List<NewsItemDTO>
}