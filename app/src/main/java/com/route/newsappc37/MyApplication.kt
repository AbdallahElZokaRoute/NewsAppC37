package com.route.newsappc37

import android.app.Application
import com.route.newsappc37.database.NewsDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NewsDatabase.getInstance(this)
    }
}