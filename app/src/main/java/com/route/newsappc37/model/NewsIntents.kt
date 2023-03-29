package com.route.newsappc37.model

sealed class NewsIntents {
    class SelectedTab(val idOfSource: String) : NewsIntents()
    object Idle : NewsIntents()
}
