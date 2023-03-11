package com.route.newsappc37.di

import com.route.newsappc37.repos.NetworkHandler
import com.route.newsappc37.repos.NetworkHandlerImpl
import com.route.newsappc37.repos.news.NewsOnlineDataSource
import com.route.newsappc37.repos.news.NewsRepository
import com.route.newsappc37.repos.news.NewsRepositoryImpl
import com.route.newsappc37.repos.sources.SourcesRepository
import com.route.newsappc37.repos.sources.SourcesRepositoryImpl
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