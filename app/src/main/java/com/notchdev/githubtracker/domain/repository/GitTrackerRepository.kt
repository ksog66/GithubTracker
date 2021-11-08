package com.notchdev.githubtracker.domain.repository

import com.notchdev.githubtracker.data.remote.dto.*

interface GitTrackerRepository {

    suspend fun getRepoBranches(ownerName:String,repoName:String): List<BranchResponseItem>

    suspend fun getRepoDetail(ownerName: String, repoName: String): RepositoryResponse

    suspend fun getRepoIssues(ownerName: String, repoName: String): List<IssueResponseItem>

    suspend fun getRepoCommits(
        ownerName: String,
        repoName: String,
        branchName: String
    ): List<CommitResponseItem>
}