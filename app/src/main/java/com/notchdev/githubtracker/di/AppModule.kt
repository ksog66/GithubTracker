package com.notchdev.githubtracker.di

import com.notchdev.githubtracker.common.Constants
import com.notchdev.githubtracker.common.DispatcherProvider
import com.notchdev.githubtracker.data.remote.GithubApi
import com.notchdev.githubtracker.data.repository.GitTrackerRepositoryImpl
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideGitTrackerRepository(
        api: GithubApi
    ): GitTrackerRepository {
        return GitTrackerRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGitTrackerApi(): GithubApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GithubApi::class.java)
    }
}