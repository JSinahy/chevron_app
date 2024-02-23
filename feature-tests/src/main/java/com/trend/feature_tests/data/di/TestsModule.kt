package com.trend.feature_tests.data.di

import com.trend.feature_common.network.ApiServices
import com.trend.feature_tests.data.ds.TestRemoteDataSource
import com.trend.feature_tests.data.ds.TestRemoteDataSourceImpl
import com.trend.feature_tests.data.repository.TestsRepository
import com.trend.feature_tests.data.repository.TestsRepositoryImpl
import com.trend.feature_tests.domain.usecases.GetTestsUseCase
import com.trend.feature_tests.domain.usecases.GetTestsUseCaseImpl
import com.trend.feature_tests.domain.usecases.UpdateStatusLessonsUseCase
import com.trend.feature_tests.domain.usecases.UpdateStatusLessonsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestsModule {
    /** CONTENT **/
    @Singleton
    @Provides
    fun providesTestsRemoteDataSource(apiServices: ApiServices): TestRemoteDataSource =
        TestRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesTestsRepository(testsRemoteDataSource: TestRemoteDataSource): TestsRepository =
        TestsRepositoryImpl(testsRemoteDataSource)

    @Singleton
    @Provides
    fun providesGetTestsUseCase(testsRepository: TestsRepository): GetTestsUseCase =
        GetTestsUseCaseImpl(testsRepository)

    @Singleton
    @Provides
    fun providesUpdateStatusLessonsUseCase(testsRepository: TestsRepository): UpdateStatusLessonsUseCase =
        UpdateStatusLessonsUseCaseImpl(testsRepository)

}