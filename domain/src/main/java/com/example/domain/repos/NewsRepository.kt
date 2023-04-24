package com.example.domain.repos

import com.example.domain.entities.NetworkResponse
import com.example.domain.entities.NewsItemDTO
import dagger.Binds
import dagger.Provides
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getNews(source: String) : Flow<NetworkResponse<List<NewsItemDTO>>>

}

interface NewsOnlineDataSource{

    suspend fun getNews(source: String) : Flow<NetworkResponse<List<NewsItemDTO>>>
}