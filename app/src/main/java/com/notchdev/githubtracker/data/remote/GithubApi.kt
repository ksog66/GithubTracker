package com.notchdev.githubtracker.data.remote

import com.notchdev.githubtracker.data.remote.dto.BranchResponse
import com.notchdev.githubtracker.data.remote.dto.CommitResponse
import com.notchdev.githubtracker.data.remote.dto.IssueResponse
import com.notchdev.githubtracker.data.remote.dto.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubApi {

    @GET("/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryResponse

    @GET("/{owner}/{repo}/branches")
    suspend fun getBranchesOfRepo(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
    ):BranchResponse

    @GET("/{owner}/{repo}/commits")
    suspend fun getCommitsOfBranch(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
        @Query("sha") branchName:String
    ): CommitResponse

    @GET("/{owner}/{repo}/issues")
    suspend fun getOpenIssuesOfRepo(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
        @Query("state") state:String = "open"
    ): IssueResponse
}