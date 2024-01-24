package com.trend.feature_accounts.data.di

import com.trend.feature_accounts.data.ds.AccountsRemoteDataSource
import com.trend.feature_accounts.data.ds.AccountsRemoteDataSourceImpl
import com.trend.feature_accounts.data.ds.CitiesRemoteDataSource
import com.trend.feature_accounts.data.ds.CitiesRemoteDataSourceImpl
import com.trend.feature_accounts.data.ds.CountriesRemoteDataSource
import com.trend.feature_accounts.data.ds.CountriesRemoteDataSourceImpl
import com.trend.feature_accounts.data.ds.ServiceStationsRemoteDataSource
import com.trend.feature_accounts.data.ds.ServiceStationsRemoteDataSourceImpl
import com.trend.feature_accounts.data.repositories.AccountsRepository
import com.trend.feature_accounts.data.repositories.AccountsRepositoryImpl
import com.trend.feature_accounts.data.repositories.CitiesRepository
import com.trend.feature_accounts.data.repositories.CitiesRepositoryImpl
import com.trend.feature_accounts.data.repositories.CountriesRepository
import com.trend.feature_accounts.data.repositories.CountriesRepositoryImpl
import com.trend.feature_accounts.data.repositories.ServiceStationsRepository
import com.trend.feature_accounts.data.repositories.ServiceStationsRepositoryImpl
import com.trend.feature_accounts.domain.usecases.CitiesUseCase
import com.trend.feature_accounts.domain.usecases.CitiesUseCaseImpl
import com.trend.feature_accounts.domain.usecases.CountriesUseCase
import com.trend.feature_accounts.domain.usecases.CountriesUseCaseImpl
import com.trend.feature_accounts.domain.usecases.DeleteUsersUseCase
import com.trend.feature_accounts.domain.usecases.DeleteUsersUseCaseImpl
import com.trend.feature_accounts.domain.usecases.SaveUserUseCase
import com.trend.feature_accounts.domain.usecases.SaveUserUseCaseImpl
import com.trend.feature_accounts.domain.usecases.ServiceStationsUseCase
import com.trend.feature_accounts.domain.usecases.ServiceStationsUseCaseImpl
import com.trend.feature_accounts.domain.usecases.UpdateUsersUseCase
import com.trend.feature_accounts.domain.usecases.UpdateUsersUseCaseImpl
import com.trend.feature_common.network.ApiServices
import com.trend.feature_common.network.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountsModule {

    @Singleton
    @Provides
    fun providesApiServices(): ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

    /** COUNTRIES **/
    @Singleton
    @Provides
    fun providesCountriesRemoteDataSource(apiServices: ApiServices): CountriesRemoteDataSource =
        CountriesRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesCountriesRepository(countriesRemoteDataSource: CountriesRemoteDataSource): CountriesRepository =
        CountriesRepositoryImpl(countriesRemoteDataSource)

    @Singleton
    @Provides
    fun providesCountriesUseCase(countriesRepository: CountriesRepository): CountriesUseCase =
        CountriesUseCaseImpl(countriesRepository)

    /** CITIES **/
    @Singleton
    @Provides
    fun providesCitiesRemoteDataSource(apiServices: ApiServices): CitiesRemoteDataSource =
        CitiesRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesCitiesRepository(citiesRemoteDataSource: CitiesRemoteDataSource): CitiesRepository =
        CitiesRepositoryImpl(citiesRemoteDataSource)

    @Singleton
    @Provides
    fun providesCitiesUseCase(citiesRepository: CitiesRepository): CitiesUseCase =
        CitiesUseCaseImpl(citiesRepository)

    /** SERVICE STATIONS **/
    @Singleton
    @Provides
    fun providesServiceStationsRemoteDataSource(apiServices: ApiServices): ServiceStationsRemoteDataSource =
        ServiceStationsRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesServiceStationsRepository(serviceStationsRemoteDataSource: ServiceStationsRemoteDataSource): ServiceStationsRepository =
        ServiceStationsRepositoryImpl(serviceStationsRemoteDataSource)

    @Singleton
    @Provides
    fun providesServiceStationsUseCase(serviceStationsRepository: ServiceStationsRepository): ServiceStationsUseCase =
        ServiceStationsUseCaseImpl(serviceStationsRepository)

    /** ACCOUNTS **/
    @Singleton
    @Provides
    fun providesAccountsRemoteDataSource(apiServices: ApiServices): AccountsRemoteDataSource =
        AccountsRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesAccountsRepository(accountsRemoteDataSource: AccountsRemoteDataSource): AccountsRepository =
        AccountsRepositoryImpl(accountsRemoteDataSource)

    @Singleton
    @Provides
    fun providesSaveUserUseCase(accountsRepository: AccountsRepository): SaveUserUseCase =
        SaveUserUseCaseImpl(accountsRepository)

    @Singleton
    @Provides
    fun providesUpdateUsersUseCase(accountsRepository: AccountsRepository): UpdateUsersUseCase =
        UpdateUsersUseCaseImpl(accountsRepository)

    @Singleton
    @Provides
    fun providesDeleteUsersUseCase(accountsRepository: AccountsRepository): DeleteUsersUseCase =
        DeleteUsersUseCaseImpl(accountsRepository)
}