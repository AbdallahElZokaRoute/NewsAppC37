package com.route.newsappc37.di

import com.route.newsappc37.repos.news.NewsOnlineDataSource
import com.route.newsappc37.repos.news.NewsOnlineDataSourceImpl
import com.route.newsappc37.repos.sources.SourcesOfflineDataSource
import com.route.newsappc37.repos.sources.SourcesOfflineDataSourceImpl
import com.route.newsappc37.repos.sources.SourcesOnlineDataSource
import com.route.newsappc37.repos.sources.SourcesOnlineDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsNewsOnlineDataSource(
        newsOnlineDataSource: NewsOnlineDataSourceImpl
    ): NewsOnlineDataSource

    @Binds
    abstract fun bindsSourcesOnlineDataSource(
        sourcesOnlineDataSourceImpl: SourcesOnlineDataSourceImpl
    ): SourcesOnlineDataSource

    @Binds
    abstract fun bindsSourcesOfflineDataSource(
        sourcesOfflineDataSourceImpl: SourcesOfflineDataSourceImpl
    ): SourcesOfflineDataSource
}