package com.notchdev.githubtracker.data.repository

import com.notchdev.githubtracker.data.remote.GithubApi
import com.notchdev.githubtracker.data.remote.dto.CommitResponse
import com.notchdev.githubtracker.data.remote.dto.IssueResponse
import com.notchdev.githubtracker.data.remote.dto.RepositoryResponse
import com.notchdev.githubtracker.domain.repository.GitTrackerRepository
import javax.inject.Inject

class GitTrackerRepositoryImpl @Inject constructor(
    private val gitRepoApi:GithubApi
): GitTrackerRepository {
    override suspend fun getRepoDetail(ownerName: String, repoName: String): RepositoryResponse {
        return gitRepoApi.getRepository(ownerName,repoName)
    }

    override suspend fun getRepoIssues(ownerName: String, repoName: String): IssueResponse {
        return gitRepoApi.getOpenIssuesOfRepo(ownerName,repoName)
    }

    override suspend fun getRepoCommits(
        ownerName: String,
        repoName: String,
        branchName: String
    ): CommitResponse {
        return gitRepoApi.getCommitsOfBranch(
            ownerName,
            repoName,
            branchName
        )
    }
}