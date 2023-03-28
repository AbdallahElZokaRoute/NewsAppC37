package com.example.data.di

import com.example.data.online.news.NewsOnlineDataSourceImpl
import com.example.data.online.sources.SourcesOfflineDataSourceImpl
import com.example.data.online.sources.SourcesOnlineDataSourceImpl
import com.example.domain.repos.NewsOnlineDataSource
import com.example.domain.repos.SourcesOfflineDataSource
import com.example.domain.repos.SourcesOnlineDataSource

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

@Binds
   abstract fun bindsNewsDataSource(newsOnlineDataSourceImpl: NewsOnlineDataSourceImpl) : NewsOnlineDataSource
   @Binds
   abstract fun bindsSourcesOnlineDataSource(sourcesOnlineDataSourceImpl: SourcesOnlineDataSourceImpl) : SourcesOnlineDataSource
   @Binds
   abstract fun bindsSourcesOfflineDatasource(sourcesOfflineDataSourceImpl: SourcesOfflineDataSourceImpl) : SourcesOfflineDataSource

}