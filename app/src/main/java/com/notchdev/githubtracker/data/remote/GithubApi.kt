package com.notchdev.githubtracker.data.remote

import com.notchdev.githubtracker.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubApi {

    @GET("{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryResponse

    @GET("{owner}/{repo}/branches")
    suspend fun getBranchesOfRepo(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
    ):List<BranchResponseItem>

    @GET("{owner}/{repo}/commits")
    suspend fun getCommitsOfBranch(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
        @Query("sha") branchName:String
    ): List<CommitResponseItem>

    @GET("{owner}/{repo}/issues")
    suspend fun getOpenIssuesOfRepo(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
        @Query("state") state:String = "open"
    ): List<IssueResponseItem>
}