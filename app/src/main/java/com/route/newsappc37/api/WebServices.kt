package com.route.newsappc37.api

import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNewsBySource(
        @Query("apiKey") apiKeyAuthentication: String,
        @Query("sources") source: String
    ): Call<NewsResponse>
}
