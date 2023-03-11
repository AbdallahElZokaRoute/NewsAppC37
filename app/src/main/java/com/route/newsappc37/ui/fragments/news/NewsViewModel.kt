package com.route.newsappc37.ui.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc37.Constants
import com.route.newsappc37.model.ArticlesItem
import com.route.newsappc37.model.NewsResponse
import com.route.newsappc37.model.SourcesItem
import com.route.newsappc37.repos.NetworkHandler
import com.route.newsappc37.repos.news.NewsOnlineDataSource
import com.route.newsappc37.repos.news.NewsOnlineDataSourceImpl
import com.route.newsappc37.repos.news.NewsRepository
import com.route.newsappc37.repos.news.NewsRepositoryImpl
import com.route.newsappc37.repos.sources.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val sourcesRepository: SourcesRepository
) : ViewModel() {

    //Directed ACyclic Graph
    //DAGGER
    /**
     * Two Steps ->
     *      1- Provide or Binds Dependency -> Create Dependencies
     *      2- Inject Dependencies -> Use Dependencies(Already created in previous step)
     *
     */


    val articlesLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    var parentJob: Job? = null
    fun getNewsBySource(item: SourcesItem?) {

        viewModelScope.launch {
            parentJob?.join()
            try {
                val response =
                    newsRepository.getNews(item?.id!!)
                loadingLiveData.value = false
                articlesLiveData.value = response
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
                    val response = sourcesRepository.getSources(
                        NewsFragment.selectedCategory.apiID,
                    )

                    sourcesLiveData.postValue(response)

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
