package com.route.domain.di


import com.route.domain.repos.NewsRepository
import com.route.domain.repos.SourcesRepository
import com.route.domain.usecases.GetNewsUseCase
import com.route.domain.usecases.GetSourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun providesNewsUseCase(
        newsRepository: NewsRepository
    ): GetNewsUseCase {
        return GetNewsUseCase(newsRepository)
    }

    @Provides
    fun providesSourcesUseCase(
        sourcesRepository: SourcesRepository
    ): GetSourcesUseCase {
        return GetSourcesUseCase(sourcesRepository)
    }
}