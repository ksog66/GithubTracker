package com.notchdev.githubtracker.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val ownerName:String,
    val repoName:String
)