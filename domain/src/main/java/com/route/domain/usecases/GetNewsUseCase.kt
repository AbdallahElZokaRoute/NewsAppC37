package com.route.domain.usecases

import com.route.domain.entity.NewsItemDTO
import com.route.domain.repos.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(source: String): List<NewsItemDTO> {
        if (source.isBlank())
            return emptyList()
        return newsRepository.getNews(source)
    }
}
