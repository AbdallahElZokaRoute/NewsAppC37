package com.example.data.di

import com.example.data.online.networkHandler.NetworkHandlerImpl
import com.example.data.online.news.NewsRepositoryImpl
import com.example.data.online.sources.SourcesRepositoryImpl
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SourcesRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun  providesNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository
@Binds
@Singleton
    abstract fun providesSourcesRepository(sourcesRepositoryImpl: SourcesRepositoryImpl) : SourcesRepository

@Binds
@Singleton
 abstract fun providesNetworkHandler(networkHandlerImpl: NetworkHandlerImpl) : NetworkHandler

}