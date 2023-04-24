package com.route.newsappc37.model

import com.example.domain.entities.NewsItemDTO

sealed class NewsViewStates{
    object LoadingState : NewsViewStates()
    class LoadedState(val newsList: List<NewsItemDTO>) : NewsViewStates()
    object EmptyState: NewsViewStates()
    class Error(val message : String): NewsViewStates()
}
