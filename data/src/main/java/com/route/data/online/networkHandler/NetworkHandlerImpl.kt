package com.route.data.online.networkHandler

import com.route.domain.repos.NetworkHandler
import javax.inject.Inject

class NetworkHandlerImpl @Inject constructor() : NetworkHandler {
    override fun isOnline(): Boolean {
        return true
    }
}