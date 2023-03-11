package com.route.data.di

import com.route.data.online.networkHandler.NetworkHandlerImpl
import com.route.data.online.news.NewsRepositoryImpl
import com.route.data.online.sources.SourcesRepositoryImpl
import com.route.domain.repos.NetworkHandler
import com.route.domain.repos.NewsRepository
import com.route.domain.repos.SourcesRepository

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    //@Provides or
    @Binds
    @Singleton
    abstract fun providesNewsRepository(
        newsRepoImpl: NewsRepositoryImpl
    ): NewsRepository

//    @Provides
//    fun providesNewsRepository22(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
//        return NewsRepositoryImpl(newsOnlineDataSource)
//    }

    @Binds
    @Singleton
    abstract fun providesSourcesRepository(
        sourcesRepository: SourcesRepositoryImpl
    ): SourcesRepository

    @Binds
    @Singleton
    abstract fun providesNetworkHandler(
        networkHandlerImpl: NetworkHandlerImpl
    ): NetworkHandler
}