package com.route.domain.di

import com.route.domain.repos.NewsRepository
import com.route.domain.reposImplForTesting.FakeNewsRepositoryImpl
import com.route.domain.usecases.GetNewsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Named("testing_news_repository")
    fun providesFakeNewsRepository(): NewsRepository = FakeNewsRepositoryImpl()

    @Provides
    @Named("testing_news_usecase")
    fun providesTestingsNewsUseCase(@Named("testing_news_repository") repository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(repository)
    }
}