package com.route.data.di

import com.route.data.online.news.NewsOnlineDataSourceImpl
import com.route.data.online.sources.SourcesOfflineDataSourceImpl
import com.route.data.online.sources.SourcesOnlineDataSourceImpl
import com.route.domain.repos.NewsOnlineDataSource
import com.route.domain.repos.SourcesOfflineDataSource
import com.route.domain.repos.SourcesOnlineDataSource

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