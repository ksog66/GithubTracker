package com.notchdev.githubtracker.data.remote.dto


import com.notchdev.githubtracker.domain.model.BranchDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BranchResponseItem(
    @Json(name = "commit")
    val commit: Commit,
    @Json(name = "name")
    val name: String,
    @Json(name = "protected")
    val `protected`: Boolean
) {
    fun toBranchDetail(): BranchDetail {
        return BranchDetail(
            branchName = name,
            sha = commit.sha
        )
    }
}