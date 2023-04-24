package com.route.newsappc37.ui.fragments.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.NetworkResponse
import com.example.domain.entities.NewsItemDTO
import com.example.domain.entities.SourcesItemDTO
import com.example.domain.usecases.GetNewsUSeCase
import com.example.domain.usecases.GetSourcesUseCase
import com.route.newsappc37.model.NewsIntents
import com.route.newsappc37.model.NewsViewStates

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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
    var parentJob: Job? = null
    var sourcesItemIdStateFlow = MutableStateFlow<String>("")
    val newsViewStates = MutableStateFlow<NewsViewStates>(NewsViewStates.LoadingState )
    val newsIntents = Channel<NewsIntents>()
    val newsResponseFlow: Flow<NetworkResponse<List<NewsItemDTO>>>
        get() = newsChannel.consumeAsFlow()
    private val newsChannel = Channel<NetworkResponse<List<NewsItemDTO>>>()

    init {
        processNewsIntents()
    }
    fun processNewsIntents(){

        viewModelScope.launch {
            newsIntents.consumeAsFlow().collect{

                when (it) {
                    is NewsIntents.Idle -> {

                    }
                    is NewsIntents.GetNewsData -> {
                        getNewsUSeCase(it.idOfSource).collect{
                            newsChannel.send(it)
                        }
                    }
                    is NewsIntents.SelectedTab -> {
                        getNewsUSeCase(it.idOfSource).collect(){
                            newsChannel.send(it)
                        }
                    }
                }
            }
        }
    }
    fun reduceNewsViewStates() {
        viewModelScope.launch {
            newsResponseFlow.collect {
                newsViewStates.value = when (it) {
                    is NetworkResponse.Loading -> NewsViewStates.LoadingState
                    is NetworkResponse.Error -> NewsViewStates.Error(it.message)
                    is NetworkResponse.Success -> if (it.data.isEmpty()) NewsViewStates.EmptyState else NewsViewStates.LoadedState(
                        it.data

                    )
                    // Force updates -> subscription Plans
                }
            }
        }
    }






    fun getSourcesFromAPI() {
        //Jobs
        parentJob = viewModelScope.launch {
            launch(Dispatchers.IO) {
                try {
                    val response = getSourcesUseCase(
                        NewsFragment.selectedCategory.apiID,
                    )
                    Log.e("TAG", response.size.toString())
                    sourcesLiveData.postValue(response)
                } catch (ex: Exception) {
                    loadingLiveData.postValue(false)
                    messageLiveData.postValue(ex.message)
                }
            }
        }
    }
}
