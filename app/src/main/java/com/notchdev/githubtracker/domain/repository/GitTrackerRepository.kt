package com.notchdev.githubtracker.domain.repository

import com.notchdev.githubtracker.data.remote.dto.CommitResponse
import com.notchdev.githubtracker.data.remote.dto.IssueResponse
import com.notchdev.githubtracker.data.remote.dto.RepositoryResponse

interface GitTrackerRepository {

    suspend fun getRepoDetail(ownerName: String, repoName: String): RepositoryResponse

    suspend fun getRepoIssues(ownerName: String, repoName: String): IssueResponse

    suspend fun getRepoCommits(
        ownerName: String,
        repoName: String,
        branchName: String
    ): CommitResponse
}