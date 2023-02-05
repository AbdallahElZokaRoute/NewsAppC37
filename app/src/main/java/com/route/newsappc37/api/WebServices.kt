package com.route.newsappc37.api

import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getSources(@Query("apiKey") apiKeyAuthentication: String): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNewsBySource(@Query("sources") source: String): Call<NewsResponse>
}
