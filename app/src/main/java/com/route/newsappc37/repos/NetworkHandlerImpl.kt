package com.route.newsappc37.repos

import javax.inject.Inject

class NetworkHandlerImpl @Inject constructor() : NetworkHandler {
    override fun isOnline(): Boolean {
        return true
    }
}