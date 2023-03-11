package com.route.domain.entity

data class NewsItemDTO(

    val publishedAt: String? = null,


    val author: String? = null,


    val urlToImage: String? = null,


    val description: String? = null,


    val source: SourceDTO? = null,


    val title: String? = null,


    val url: String? = null,


    val content: String? = null
)

data class SourceDTO(


    val name: String? = null,


    val id: String? = null
)

