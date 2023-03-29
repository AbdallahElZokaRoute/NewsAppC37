package com.route.data.online.news

import android.util.Log
import com.route.data.online.Constants
import com.route.data.online.WebServices
import com.route.data.toDomainObject
import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsOnlineDataSource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : NewsOnlineDataSource {
    override suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>> {
        return flow<NetworkResponse<List<NewsItemDTO>>> {
            val response = webServices.getNewsBySource(Constants.API_KEY, source, "en")
            val successResponse =
                NetworkResponse.Success(response.articles?.map {
                    it.toDomainObject(
                        NewsItemDTO::class.java
                    )
                }!!)
            emit(successResponse)
        }.onStart {
            emit(NetworkResponse.Loading)
        }.catch {
            if (it !is CancellationException) {
                emit(NetworkResponse.Error(it.message ?: "Something went wrong"))
            }
        }.flowOn(Dispatchers.IO)


    }


}