package com.route.domain.entity

sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>() // 2 seconds
    class Error(val message: String) : NetworkResponse<Nothing>()
    class Success<out DATA>(val data: DATA) : NetworkResponse<DATA>()
}
