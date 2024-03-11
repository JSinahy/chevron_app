package com.trend.feature_content.data.di

import com.trend.feature_common.network.ApiServices
import com.trend.feature_common.network.RetrofitHelper
import com.trend.feature_content.data.ds.AccountsRemoteDataSource
import com.trend.feature_content.data.ds.AccountsRemoteDataSourceImpl
import com.trend.feature_content.data.ds.AuthenticationRemoteDataSource
import com.trend.feature_content.data.ds.AuthenticationRemoteDataSourceImpl
import com.trend.feature_content.data.ds.CitiesRemoteDataSource
import com.trend.feature_content.data.ds.CitiesRemoteDataSourceImpl
import com.trend.feature_content.data.ds.ContentRemoteDataSource
import com.trend.feature_content.data.ds.ContentRemoteDataSourceImpl
import com.trend.feature_content.data.ds.CountriesRemoteDataSource
import com.trend.feature_content.data.ds.CountriesRemoteDataSourceImpl
import com.trend.feature_content.data.ds.ProfilesRemoteDataSource
import com.trend.feature_content.data.ds.ProfilesRemoteDataSourceImpl
import com.trend.feature_content.data.ds.ServiceStationsRemoteDataSource
import com.trend.feature_content.data.ds.ServiceStationsRemoteDataSourceImpl
import com.trend.feature_content.data.ds.TestRemoteDataSource
import com.trend.feature_content.data.ds.TestRemoteDataSourceImpl
//import com.trend.feature_content.data.ds.TrendsRemoteDataSource
import com.trend.feature_content.data.repositories.AccountsRepository
import com.trend.feature_content.data.repositories.AccountsRepositoryImpl
import com.trend.feature_content.data.repositories.AuthenticationRepository
import com.trend.feature_content.data.repositories.AuthenticationRepositoryImpl
import com.trend.feature_content.data.repositories.CitiesRepository
import com.trend.feature_content.data.repositories.CitiesRepositoryImpl
import com.trend.feature_content.data.repositories.ContentRepository
import com.trend.feature_content.data.repositories.ContentRepositoryImpl
import com.trend.feature_content.data.repositories.CountriesRepository
import com.trend.feature_content.data.repositories.CountriesRepositoryImpl
import com.trend.feature_content.data.repositories.ProfilesRepository
import com.trend.feature_content.data.repositories.ProfilesRepositoryImpl
import com.trend.feature_content.data.repositories.ServiceStationsRepository
import com.trend.feature_content.data.repositories.ServiceStationsRepositoryImpl
import com.trend.feature_content.data.repositories.TestsRepository
import com.trend.feature_content.data.repositories.TestsRepositoryImpl
//import com.trend.feature_content.data.repositories.TrendsRepository
//import com.trend.feature_content.data.repositories.TrendsRepositoryImpl
import com.trend.feature_content.domain.usecases.CitiesUseCase
import com.trend.feature_content.domain.usecases.CitiesUseCaseImpl
import com.trend.feature_content.domain.usecases.ContentUseCase
import com.trend.feature_content.domain.usecases.ContentUseCaseImpl
import com.trend.feature_content.domain.usecases.CountriesUseCase
import com.trend.feature_content.domain.usecases.CountriesUseCaseImpl
import com.trend.feature_content.domain.usecases.DeleteUsersUseCase
import com.trend.feature_content.domain.usecases.DeleteUsersUseCaseImpl
import com.trend.feature_content.domain.usecases.GenerateOTPUseCase
import com.trend.feature_content.domain.usecases.GenerateOTPUseCaseImpl
import com.trend.feature_content.domain.usecases.GetBrandsCompletedUseCase
import com.trend.feature_content.domain.usecases.GetBrandsCompletedUseCaseImpl
import com.trend.feature_content.domain.usecases.GetTestsUseCase
import com.trend.feature_content.domain.usecases.GetTestsUseCaseImpl
//import com.trend.feature_content.domain.usecases.GetTrendsCompletedUseCase
//import com.trend.feature_content.domain.usecases.GetTrendsCompletedUseCaseImpl
import com.trend.feature_content.domain.usecases.LoginWithCellNumberUseCase
import com.trend.feature_content.domain.usecases.LoginWithCellNumberUseCaseImpl
import com.trend.feature_content.domain.usecases.SaveUserUseCase
import com.trend.feature_content.domain.usecases.SaveUserUseCaseImpl
import com.trend.feature_content.domain.usecases.ServiceStationsUseCase
import com.trend.feature_content.domain.usecases.ServiceStationsUseCaseImpl
import com.trend.feature_content.domain.usecases.UpdateLessonUseCase
import com.trend.feature_content.domain.usecases.UpdateLessonUseCaseImpl
import com.trend.feature_content.domain.usecases.UpdateStatusLessonsUseCase
import com.trend.feature_content.domain.usecases.UpdateStatusLessonsUseCaseImpl
import com.trend.feature_content.domain.usecases.UpdateUsersUseCase
import com.trend.feature_content.domain.usecases.UpdateUsersUseCaseImpl
import com.trend.feature_content.domain.usecases.ValidateOTPUseCase
import com.trend.feature_content.domain.usecases.ValidateOTPUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContentModule {

    @Singleton
    @Provides
    fun providesApiServices(): ApiServices = RetrofitHelper.getInstance().create(ApiServices::class.java)

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

    @Singleton
    @Provides
    fun providesUpdateLessonUseCase(contentRepository: ContentRepository): UpdateLessonUseCase =
        UpdateLessonUseCaseImpl(contentRepository)

    @Singleton
    @Provides
    fun providesProfilesRepository(profilesRemoteDataSource: ProfilesRemoteDataSource): ProfilesRepository =
        ProfilesRepositoryImpl(profilesRemoteDataSource)

    @Singleton
    @Provides
    fun providesProfilesRemoteDataSource(apiServices: ApiServices): ProfilesRemoteDataSource =
        ProfilesRemoteDataSourceImpl(apiServices)

    @Singleton
    @Provides
    fun providesGetBrandsCompletedUseCase(profilesRepository: ProfilesRepository): GetBrandsCompletedUseCase =
        GetBrandsCompletedUseCaseImpl(profilesRepository)

    /*@Singleton
    @Provides
    fun providesTrendsRepository(trendsRemoteDataSource: TrendsRemoteDataSource): TrendsRepository =
        TrendsRepositoryImpl(trendsRemoteDataSource)*/

    /*@Singleton
    @Provides
    fun providesGetTrendsCompletedUseCase(trendsRepository: TrendsRepository): GetTrendsCompletedUseCase =
        GetTrendsCompletedUseCaseImpl(trendsRepository)*/

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