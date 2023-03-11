package com.route.newsappc37.repos.sources

import com.route.newsappc37.Constants
import com.route.newsappc37.api.WebServices
import com.route.newsappc37.model.Category
import com.route.newsappc37.model.SourcesItem
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem> {
        try {
            val response = webServices.getSources(Constants.API_KEY, category = category, "en")
            return response.sources ?: emptyList()
        } catch (ex: Exception) {
            throw ex
        }
    }
}