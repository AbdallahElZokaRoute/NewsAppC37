package com.route.newsappc37.ui.fragments.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.NewsItemDTO
import com.example.domain.entities.SourcesItemDTO
import com.example.domain.usecases.GetNewsUSeCase
import com.example.domain.usecases.GetSourcesUseCase
import com.route.newsappc37.Constants

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

    private val getSourcesUseCase: GetSourcesUseCase,
    private val getNewsUSeCase: GetNewsUSeCase
) : ViewModel() {

    val articlesLiveData = MutableLiveData<List<NewsItemDTO?>?>()
    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItemDTO?>?>()


    fun getNewsBySource(item: SourcesItemDTO?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getNewsUSeCase(item?.id!!)

                loadingLiveData.postValue(false)
                articlesLiveData.postValue(response)
            }catch (ex : Exception){

                messageLiveData.postValue(ex.message)
                loadingLiveData.postValue(false)
            }

        }
              /*
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
        */

    }

    fun getSourcesFromAPI() {
      viewModelScope.launch {
try {
    val response = getSourcesUseCase(NewsFragment.selectedCategory.apiID)

    sourcesLiveData.postValue(response)
    loadingLiveData.value= false



}catch (ex : Exception){

    messageLiveData.value = ex.message
    loadingLiveData.value = false
}


        }
    }
            /*
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

             */
}
