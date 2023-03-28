package com.route.newsappc37.di

import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SourcesRepository
import com.example.domain.usecases.GetNewsUSeCase
import com.example.domain.usecases.GetSourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun providesNewsUseCase(newsRepository: NewsRepository): GetNewsUSeCase{

        return GetNewsUSeCase(newsRepository)
    }
    @Provides
    fun providesSourcesUseCase(sourcesRepository: SourcesRepository) : GetSourcesUseCase{

        return GetSourcesUseCase(sourcesRepository)
    }
}