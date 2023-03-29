package com.route.domain.reposImplForTesting

import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.entity.SourceDTO
import com.route.domain.repos.NewsRepository
import kotlinx.coroutines.flow.Flow
/*
class FakeNewsRepositoryImpl : NewsRepository {
    override suspend fun getNews(source: String): Flow<NetworkResponse<List<NewsItemDTO>>> {
        if (source.isBlank()) {
            return emptyList()
        }
        return listOf(
            NewsItemDTO(
                "3/18/2023",
                "Hello",
                "image",
                "description",
                SourceDTO("BBC News", "1"),
                "Title",
                "URL",
                "Content"
            ),
            NewsItemDTO(
                "3/18/2023",
                "Hello",
                "image",
                "description",
                SourceDTO("BBC News", "2"),
                "Title",
                "URL",
                "Content"
            ),
            NewsItemDTO(
                "3/18/2023",
                "Hello",
                "image",
                "description",
                SourceDTO("BBC News", "3"),
                "Title",
                "URL",
                "Content"
            ),
            NewsItemDTO(
                "3/18/2023",
                "Hello",
                "image",
                "description",
                SourceDTO("BBC News", "4"),
                "Title",
                "URL",
                "Content"
            ),


            )
    }
}

 */