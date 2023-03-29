package com.route.newsappc37.sealed_classes

import com.route.domain.entity.NewsItemDTO

/**
 *  Enum
 *  Sealed Classes
 *
 */


enum class LanguageDropDownEnum(val position: Int, val languageSelected: String) {
    LANGUAGE_ARABIC(0, "ar"),
    LANGUAGE_ENGLISH(1, "en"),

}

enum class UserTypes(val authorization: String) {
    CLIENT("client"),
    ADMIN("admin"),
}

// Flow <NetworkResponse>
//emit (Loading())
//emit (Error())
//emit (Success())

sealed interface NetworkResponse {
    object Loading : NetworkResponse // 2 seconds
    class Error(val message: String) : NetworkResponse
    class Success<DATA>(val data: DATA) : NetworkResponse
}

fun main() {
    val languageEnum = LanguageDropDownEnum.LANGUAGE_ARABIC.languageSelected
    val adminUserTypes = UserTypes.ADMIN

//    when (networkRepsonse) {
//        is NetworkResponse.Loading -> {}//show Progress bar
//        is NetworkResponse.Error -> {}//show Error Dialog
//        is NetworkResponse.Success<List<NewsItemDTO>> -> {
//            adapter.updateData(networkRepsonse.data)
//        }//List News To Recycler View
//    }
}