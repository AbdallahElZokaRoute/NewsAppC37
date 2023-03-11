package com.route.data

import com.google.gson.Gson
import com.route.domain.entity.NewsItemDTO

fun <T> Any.toDomainObject(clazz: Class<T>): T {
    val gson = Gson()
    val jsonString = gson.toJson(this)
    val type = gson.fromJson(jsonString, clazz)
    return type
}