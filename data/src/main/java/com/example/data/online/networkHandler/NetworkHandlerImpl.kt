package com.example.data.online.networkHandler

import com.example.domain.repos.NetworkHandler
import javax.inject.Inject

class NetworkHandlerImpl @Inject constructor() : NetworkHandler {
    override fun isOnline(): Boolean {
        return true
    }
}