package com.trend.feature_authentication.data.di

import com.trend.feature_authentication.data.ds.AuthenticationRemoteDataSource
import com.trend.feature_authentication.data.ds.AuthenticationRemoteDataSourceImpl
import com.trend.feature_authentication.data.repositories.AuthenticationRepository
import com.trend.feature_authentication.data.repositories.AuthenticationRepositoryImpl
import com.trend.feature_authentication.domain.usecases.GenerateOTPUseCase
import com.trend.feature_authentication.domain.usecases.GenerateOTPUseCaseImpl
import com.trend.feature_authentication.domain.usecases.LoginWithCellNumberUseCase
import com.trend.feature_authentication.domain.usecases.LoginWithCellNumberUseCaseImpl
import com.trend.feature_authentication.domain.usecases.ValidateOTPUseCase
import com.trend.feature_authentication.domain.usecases.ValidateOTPUseCaseImpl
import com.trend.feature_common.network.ApiServices
import com.trend.feature_common.network.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Singleton
    @Provides
    fun providesCAuthenticationRemoteDataSource(apiServices: ApiServices): AuthenticationRemoteDataSource =
        AuthenticationRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesAuthenticationRepository(authenticationRemoteDataSource: AuthenticationRemoteDataSource): AuthenticationRepository =
        AuthenticationRepositoryImpl(authenticationRemoteDataSource)

    @Singleton
    @Provides
    fun providesValidateOTPUseCase(authenticationRepository: AuthenticationRepository): ValidateOTPUseCase =
        ValidateOTPUseCaseImpl(authenticationRepository)

    @Singleton
    @Provides
    fun providesGenerateOTPUseCase(authenticationRepository: AuthenticationRepository): GenerateOTPUseCase =
        GenerateOTPUseCaseImpl(authenticationRepository)

    @Singleton
    @Provides
    fun providesLoginWithCellNumberUseCase(authenticationRepository: AuthenticationRepository): LoginWithCellNumberUseCase =
        LoginWithCellNumberUseCaseImpl(authenticationRepository)


}