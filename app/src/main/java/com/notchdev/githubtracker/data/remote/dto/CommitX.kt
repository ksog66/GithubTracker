package com.notchdev.githubtracker.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommitX(
    @Json(name = "author")
    val author: AuthorX?,
    @Json(name = "comment_count")
    val commentCount: Int,
    @Json(name = "committer")
    val committer: Committer,
    @Json(name = "message")
    val message: String,
    @Json(name = "tree")
    val tree: Tree,
    @Json(name = "url")
    val url: String,
    @Json(name = "verification")
    val verification: Verification
)