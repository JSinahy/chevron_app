package com.trend.feature_content.data.di

import com.trend.feature_common.network.ApiServices
import com.trend.feature_common.network.RetrofitHelper
import com.trend.feature_content.data.ds.ContentRemoteDataSource
import com.trend.feature_content.data.ds.ContentRemoteDataSourceImpl
import com.trend.feature_content.data.repositories.ContentRepository
import com.trend.feature_content.data.repositories.ContentRepositoryImpl
import com.trend.feature_content.domain.usecases.ContentUseCase
import com.trend.feature_content.domain.usecases.ContentUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentModule {

    /** CONTENT **/
    @Singleton
    @Provides
    fun providesContentRemoteDataSource(apiServices: ApiServices): ContentRemoteDataSource =
        ContentRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesContentRepository(contentRemoteDataSource: ContentRemoteDataSource): ContentRepository =
        ContentRepositoryImpl(contentRemoteDataSource)

    @Singleton
    @Provides
    fun providesContentUseCase(contentRepository: ContentRepository): ContentUseCase =
        ContentUseCaseImpl(contentRepository)
}