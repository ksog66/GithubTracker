package com.notchdev.githubtracker.data.remote.dto


import com.notchdev.githubtracker.domain.model.CommitDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommitResponseItem(
    @Json(name = "author")
    val author: Author?,
    @Json(name = "comments_url")
    val commentsUrl: String?,
    @Json(name = "commit")
    val commit: CommitX,
    @Json(name = "committer")
    val committer: CommitterX?,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "node_id")
    val nodeId: String?,
    @Json(name = "parents")
    val parents: List<Parent>?,
    @Json(name = "sha")
    val sha: String,
    @Json(name = "url")
    val url: String?
) {
    fun toCommitDetail(): CommitDetail {
        return CommitDetail(
            commitDate = commit.author?.date,
            commitMessage = commit.message,
            committerName = author?.login,
            committerAvatar = author?.avatarUrl,
            commitId = sha.substring(0..8)
        )
    }
}