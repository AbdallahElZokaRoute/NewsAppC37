package com.route.newsappc37.ui.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entity.NewsItemDTO
import com.route.domain.entity.SourcesItemDTO
import com.route.domain.usecases.GetNewsUseCase
import com.route.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase,
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    //Directed ACyclic Graph
    //DAGGER
    /**
     * Two Steps ->
     *      1- Provide or Binds Dependency -> Create Dependencies
     *      2- Inject Dependencies -> Use Dependencies(Already created in previous step)
     *
     */


    val articlesLiveData = MutableLiveData<List<NewsItemDTO?>?>()
    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItemDTO?>?>()
    var parentJob: Job? = null
    fun getNewsBySource(item: SourcesItemDTO?) {

        viewModelScope.launch {
            parentJob?.join()
            try {
                val response =
                    getNewsUseCase(item?.id!!)
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
                    val response = getSourcesUseCase(
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
