package com.example.domain.entities

sealed class NetworkResponse<out T>{

    object Loading : NetworkResponse<Nothing>()
    class Error(val message : String) : NetworkResponse<Nothing>()
    class Success<DATA>(val data : DATA) : NetworkResponse<DATA>()
}