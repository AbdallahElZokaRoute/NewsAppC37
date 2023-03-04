package com.route.newsappc37.ui.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc37.Constants
import com.route.newsappc37.api.APIManager
import com.route.newsappc37.database.NewsDatabase
import com.route.newsappc37.model.ArticlesItem
import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.repos.NetworkHandler
import com.route.newsappc37.repos.news.NewsOnlineDataSource
import com.route.newsappc37.repos.news.NewsOnlineDataSourceImpl
import com.route.newsappc37.repos.news.NewsRepository
import com.route.newsappc37.repos.news.NewsRepositoryImpl
import com.route.newsappc37.repos.sources.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    var newsRepository: NewsRepository
    var newsOnlineDataSource: NewsOnlineDataSource
    var sourcesOfflineDataSource: SourcesOfflineDataSource
    var sourcesRepository: SourcesRepository
    var sourcesOnlineDataSource: SourcesOnlineDataSource
    var networkHandler: NetworkHandler

    init {
        networkHandler = object : NetworkHandler {
            override fun isOnline(): Boolean {
                //Will be implemented in the next session
                return true
            }
        }
        sourcesOnlineDataSource = SourcesOnlineDataSourceImpl(APIManager.getWebServices())
        sourcesOfflineDataSource = SourcesOfflineDataSourceImpl(NewsDatabase.database!!)

        sourcesRepository = SourcesRepositoryImpl(
            sourcesOnlineDataSource = sourcesOnlineDataSource,
            sourcesOfflineDataSource = sourcesOfflineDataSource,
            networkHandler = networkHandler
        )
        newsOnlineDataSource = NewsOnlineDataSourceImpl(APIManager.getWebServices())
        newsRepository = NewsRepositoryImpl(newsOnlineDataSource)

    }

    val articlesLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    var parentJob: Job? = null
    fun getNewsBySource(item: SourcesItem?) {

        viewModelScope.launch {
            parentJob?.join()
            try {
                val response: NewsResponse =
                    APIManager.getWebServices().getNewsBySource(Constants.API_KEY, item?.id!!, "en")
                loadingLiveData.value = false
                articlesLiveData.value = response.articles
            } catch (ex: Exception) {
                messageLiveData.value = ex.localizedMessage
                loadingLiveData.value = false
            }

        }

//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    loadingLiveData.value = false
//                    articlesLiveData.value = response.body()?.articles
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//                    loadingLiveData.value = false
//                    messageLiveData.value = t.message
//                }
//
//            })

    }

    fun getSourcesFromAPI() {
        //Jobs
        parentJob = viewModelScope.launch {
            launch(Dispatchers.IO) {
                try {
                    val response = APIManager
                        .getWebServices()
                        .getSources(Constants.API_KEY, NewsFragment.selectedCategory.apiID, "en")
                    val sources = response.sources
                    sourcesLiveData.postValue(sources)

                } catch (ex: Exception) {
                    loadingLiveData.postValue(false)
                    messageLiveData.postValue(ex.message)

                }
            }

        }

        //            .enqueue(object : Callback<SourcesResponse> {
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    Log.e("Tag", "Error")
//                    loadingLiveData.value = false
//                    messageLiveData.value = t.message
//                }
//
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    loadingLiveData.value = false
//                    val sources = response.body()?.sources
//                    sourcesLiveData.value = sources
//                }
//            })
    }
}
