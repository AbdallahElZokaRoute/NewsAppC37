package com.example.data.online.news

import com.example.data.online.Constants
import com.example.data.online.WebServices
import com.example.data.toDomainobject
import com.example.domain.entities.NetworkResponse
import com.example.domain.entities.NewsItemDTO
import com.example.domain.repos.NewsOnlineDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(val webServices : WebServices) : NewsOnlineDataSource {
    override suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>> {




            val response = webServices.getNewsBySource(Constants.API_KEY,source)
            return flow<NetworkResponse<List<NewsItemDTO>>> {
                val successResponse = NetworkResponse.Success(response.articles?.map {
                it.toDomainobject(NewsItemDTO::class.java)
            }!!) //???????????????????????
                emit(successResponse)

            }.onStart {
emit(NetworkResponse.Loading)
            }

                .catch {
                emit(NetworkResponse.Error(it.message?:"Something Went Wrong"))
            }.flowOn(Dispatchers.IO )



}}