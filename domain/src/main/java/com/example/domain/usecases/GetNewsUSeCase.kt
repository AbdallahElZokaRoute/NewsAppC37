package com.example.domain.usecases

import com.example.domain.entities.NewsItemDTO
import com.example.domain.repos.NewsRepository
import javax.inject.Inject

class GetNewsUSeCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

suspend operator fun invoke(source : String): List<NewsItemDTO> = newsRepository.getNews(source)


}