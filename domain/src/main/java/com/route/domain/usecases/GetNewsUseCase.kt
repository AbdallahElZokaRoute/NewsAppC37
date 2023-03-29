package com.route.domain.usecases

import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(source: String): Flow<NetworkResponse<List<NewsItemDTO>>> {
        return newsRepository.getNews(source)
    }
}
