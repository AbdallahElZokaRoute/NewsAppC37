package com.example.data.online.sources


import com.example.data.online.Constants
import com.example.data.online.WebServices
import com.example.data.toDomainobject
import com.example.domain.entities.SourcesItemDTO
import com.example.domain.repos.SourcesOnlineDataSource

import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(val webServices: WebServices) :
    SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItemDTO> {

        try {
            val response = webServices.getSources(Constants.API_KEY,category)
            return response.sources?.map {
                it.toDomainobject(SourcesItemDTO::class.java)
            } ?: emptyList()


            }
        catch (ex : Exception){
throw ex

        }


    }
}