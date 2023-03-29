package com.route.domain.repos

import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {      //Flow<NetworkResponse>
    //    List<NewsItemDTO>
    suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>>
}

interface NewsOnlineDataSource {    // Flow<NetworkResponse>
    suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>>
}