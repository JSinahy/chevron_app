package com.trend.feature_trends.data.di

import com.trend.feature_common.network.ApiServices
import com.trend.feature_trends.data.ds.TrendsRemoteDataSource
import com.trend.feature_trends.data.ds.TrendsRemoteDataSourceImpl
import com.trend.feature_trends.data.repositories.TrendsRepository
import com.trend.feature_trends.data.repositories.TrendsRepositoryImpl
import com.trend.feature_trends.domain.usecases.GetTrendsCompletedUseCase
import com.trend.feature_trends.domain.usecases.GetTrendsCompletedUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrendsModule {

    @Singleton
    @Provides
    fun providesTrendsRemoteDataSource(apiServices: ApiServices): TrendsRemoteDataSource =
        TrendsRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesTrendsRepository(trendsRemoteDataSource: TrendsRemoteDataSource): TrendsRepository =
        TrendsRepositoryImpl(trendsRemoteDataSource)

    @Singleton
    @Provides
    fun providesGetTrendsCompletedUseCase(trendsRepository: TrendsRepository): GetTrendsCompletedUseCase =
        GetTrendsCompletedUseCaseImpl(trendsRepository)
}