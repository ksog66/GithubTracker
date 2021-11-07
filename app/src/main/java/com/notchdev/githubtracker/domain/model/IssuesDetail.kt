package com.notchdev.githubtracker.domain.model

data class IssuesDetail(
    val repoName:String,
    val issueTitle:String,
    val avatarUrl:String,
    val issueCreatorName:String
)