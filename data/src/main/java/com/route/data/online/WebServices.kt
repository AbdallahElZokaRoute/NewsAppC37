package com.route.data.online

import com.route.data.model.NewsResponse
import com.route.data.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("category") category: String,
        @Query("language") language: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNewsBySource(
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("sources") source: String,
        @Query("language") language: String
    ): NewsResponse
}
