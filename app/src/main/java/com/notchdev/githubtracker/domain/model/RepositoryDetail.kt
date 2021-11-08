package com.notchdev.githubtracker.domain.model

data class RepositoryDetail(
    val ownerName:String,
    val repoName:String,
    val repoDesc:String?,
    val repoLink:String
)