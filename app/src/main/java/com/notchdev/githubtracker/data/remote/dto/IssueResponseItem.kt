package com.notchdev.githubtracker.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IssueResponseItem(
    @Json(name = "active_lock_reason")
    val activeLockReason: Any?,
    @Json(name = "assignee")
    val assignee: Assignee?,
    @Json(name = "assignees")
    val assignees: List<Assignee>,
    @Json(name = "author_association")
    val authorAssociation: String,
    @Json(name = "body")
    val body: String,
    @Json(name = "closed_at")
    val closedAt: Any?,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "comments_url")
    val commentsUrl: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "labels")
    val labels: List<Label>,
    @Json(name = "labels_url")
    val labelsUrl: String,
    @Json(name = "locked")
    val locked: Boolean,
    @Json(name = "milestone")
    val milestone: Any?,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "number")
    val number: Int,
    @Json(name = "performed_via_github_app")
    val performedViaGithubApp: Any?,
    @Json(name = "reactions")
    val reactions: Reactions,
    @Json(name = "repository_url")
    val repositoryUrl: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "timeline_url")
    val timelineUrl: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "user")
    val user: User
)