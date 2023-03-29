package com.route.newsappc37.ui.fragments.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entity.NetworkResponse
import com.route.domain.entity.NewsItemDTO
import com.route.domain.entity.SourcesItemDTO
import com.route.domain.usecases.GetNewsUseCase
import com.route.domain.usecases.GetSourcesUseCase
import com.route.newsappc37.model.NewsIntents
import com.route.newsappc37.model.NewsViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
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
    // Eager Initialization
//    val hello = "World"
//    val newsChannel = Channel<NetworkResponse<List<NewsItemDTO>>>()
//    val newsFlow: Flow<NetworkResponse<List<NewsItemDTO>>>
//        get() = sourcesItemIdStateFlow.flatMapLatest { sourceId ->
//            getNewsUseCase(sourceId)
//        }
    val newsResponseFlow: Flow<NetworkResponse<List<NewsItemDTO>>>
        get() = newsChannel.consumeAsFlow()
    private val newsChannel = Channel<NetworkResponse<List<NewsItemDTO>>>()
    val newsViewStates = MutableStateFlow<NewsViewStates>(NewsViewStates.EmptyState)
    val newsIntents = Channel<NewsIntents>()
    val sourcesItemIdStateFlow = MutableStateFlow("")
    val channel = Channel<Int>()
    val stateFlow = MutableStateFlow<Int>(0) // Instead of livedata
    val list = listOf<Int>(0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7, 7, 8, 9, 9)
    fun triggerStateFlow() {
        viewModelScope.launch {
            for (i in list) {
                delay(1500)
                stateFlow.value = i
            }

        }

    }

    init {
        processNewsIntents()
//        // Kotlin Channel
//        /*viewModelScope.launch {
//            launch {
//                // Production - Producer
//                for (i in 0..10) {
//                    Log.e("TAG123", "Send $i: ")
//                    channel.send(i) //0
//                    delay(2000)
//                    /*
//                        Send Data to other coroutine -> 2 seconds
//
//
//
//                     */
//                }
//            }
//
//            launch {
//                // Consumption - Consume
//                for (i in channel) {
//                    delay(1000)
//                    Log.e("TAG123", "Received 1: $i") //0
//
//                }
//                // Other Receives Data every 1 second
//            }
//
//            launch {
//                for (i in channel) {
//                    delay(500)
//                    Log.e("TAG123", "Receiver 2: $i")
//
//                }
//            }
//
//        }
//        */
//        //Kotlin Flow
//        viewModelScope.launch {
//            val flow1 = flow<Int> {
//                for (i in 0..10) {
//                    //Producer 1 , 2
//                    emit(i)
//                    delay(2000)
//                }
//
//            }
//            launch {
//                flow1.buffer().collect {
//                    delay(1000)
//                    //Collector 1
//                    Log.e("TAG123", "Collected 1: $it")
//                }
//
//            }
//            launch {// Buffer 2
//                flow1.buffer().collect {
//                    delay(1000)
//                    //Collector 2
//                    Log.e("TAG123", "Collected 2: $it")
//                }
//            }
//
//        }

    }

    //Process Intent
    private fun processNewsIntents() {
        // Receive Intents and process It to an action
        viewModelScope.launch {
            newsIntents.consumeAsFlow().collect {
                when (it) {
                    is NewsIntents.Idle -> {

                    }
                    is NewsIntents.SelectedTab -> {
                        getNewsUseCase(it.idOfSource).collect { networkResponse ->
                            newsChannel.send(networkResponse)
                        }
                    }
                }
            }
        }
    }

    // Reduce States
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

    val loadingLiveData = MutableLiveData<Boolean>(true)
    val messageLiveData = MutableLiveData<String>()
    val sourcesLiveData = MutableLiveData<List<SourcesItemDTO?>?>()
    var parentJob: Job? = null
//    fun getNewsBySource(item: String?) {
//        viewModelScope.launch {
//            if (item != null && item != "")
//                getNewsUseCase(item).collect {
//                    //1- Create A Channel Then send states to that channel
//                    //2- Create State Flow That holds SourceItemDTO id then flatMapLatest
//                    // -> when there is a change in state flow -> a block of code executes
//                    newsChannel.send(it)
//                }
//        }
//
////            .enqueue(object : Callback<NewsResponse> {
////                override fun onResponse(
////                    call: Call<NewsResponse>,
////                    response: Response<NewsResponse>
////                ) {
////                    loadingLiveData.value = false
////                    articlesLiveData.value = response.body()?.articles
////                }
////
////                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
////                    loadingLiveData.value = false
////                    messageLiveData.value = t.message
////                }
////
////            })
//
//    }

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
