package com.notchdev.githubtracker.domain.model

data class CommitDetail(
    val commitDate:String,
    val commitMessage:String,
    val committerName:String?,
    val committerAvatar:String?,
    val commitId:String
)