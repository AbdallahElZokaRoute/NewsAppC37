package com.route.data.online.sources

import com.route.data.online.Constants
import com.route.data.online.WebServices
import com.route.data.toDomainObject
import com.route.domain.entity.SourcesItemDTO
import com.route.domain.repos.SourcesOnlineDataSource
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(
    val webServices: WebServices
) : SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItemDTO> {
        try {
            val response = webServices.getSources(Constants.API_KEY, category = category, "en")
            return response.sources?.map {
                it.toDomainObject(SourcesItemDTO::class.java)
            } ?: emptyList()
        } catch (ex: Exception) {
            throw ex
        }
    }
}