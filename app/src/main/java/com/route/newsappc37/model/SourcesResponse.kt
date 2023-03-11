package com.route.newsappc37.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @field:SerializedName("sources")
    val sources: List<SourcesItem>? = null,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("status")
    val status: String? = null
)

@Entity
data class SourcesItem(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("language")
    val language: String? = null,
    @PrimaryKey
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("url")
    val url: String? = null
)
