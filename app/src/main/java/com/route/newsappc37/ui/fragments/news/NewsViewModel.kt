package com.route.newsappc37.ui.fragments.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsappc37.Constants
import com.route.newsappc37.api.APIManager
import com.route.newsappc37.model.ArticlesItem
import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val articlesLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    fun getNewsBySource(item: SourcesItem?) {
        APIManager.getWebServices()
            .getNewsBySource(Constants.API_KEY, item?.id!!)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    loadingLiveData.value = false
                    articlesLiveData.value = response.body()?.articles
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    loadingLiveData.value = false
                    messageLiveData.value = t.message
                }

            })

    }

    fun getSourcesFromAPI() {
        APIManager
            .getWebServices()
            .getSources(Constants.API_KEY, NewsFragment.selectedCategory.apiID)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("Tag", "Error")
                    loadingLiveData.value = false
                    messageLiveData.value = t.message
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    loadingLiveData.value = false
                    val sources = response.body()?.sources
                    sourcesLiveData.value = sources
                }
            })
    }
}
