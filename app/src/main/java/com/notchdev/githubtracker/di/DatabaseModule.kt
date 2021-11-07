package com.notchdev.githubtracker.di

import android.content.Context
import androidx.room.Room
import com.notchdev.githubtracker.data.local.GitRepoDatabase
import com.notchdev.githubtracker.data.local.RepositoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRepositoryDao(db:GitRepoDatabase): RepositoryDao {
        return db.repositoryDao()
    }

    @Provides
    @Singleton
    fun provideGitRepoDatabase(
        @ApplicationContext app:Context): GitRepoDatabase {
        return Room.databaseBuilder(
            app,
            GitRepoDatabase::class.java,
            "gitRepo.db"
        ).build()
    }

}