package com.notchdev.githubtracker.di

import com.notchdev.githubtracker.presentation.adapter.BranchAdapter
import com.notchdev.githubtracker.presentation.adapter.CommitAdapter
import com.notchdev.githubtracker.presentation.adapter.IssuesAdapter
import com.notchdev.githubtracker.presentation.adapter.RepositoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object AdapterModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRepositoryAdapter(): RepositoryAdapter = RepositoryAdapter()

    @Provides
    @ActivityRetainedScoped
    fun provideBranchAdapter(): BranchAdapter = BranchAdapter()

    @Provides
    @ActivityRetainedScoped
    fun provideIssuesAdapter(): IssuesAdapter = IssuesAdapter()

    @Provides
    @ActivityRetainedScoped
    fun provideCommitAdapter(): CommitAdapter = CommitAdapter()
}