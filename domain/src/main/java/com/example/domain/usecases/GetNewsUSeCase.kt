package com.example.domain.usecases

import android.util.Log
import com.example.domain.entities.NetworkResponse
import com.example.domain.entities.NewsItemDTO

import com.example.domain.repos.NewsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetNewsUSeCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

suspend operator fun invoke(source : String): Flow<NetworkResponse<List<NewsItemDTO>>> {
    Log.e("hih", newsRepository.getNews(source).toString() )
    return newsRepository.getNews(source)


}
}