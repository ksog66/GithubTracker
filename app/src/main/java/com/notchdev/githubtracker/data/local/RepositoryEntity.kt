package com.notchdev.githubtracker.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class RepositoryDetail(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var ownerName:String,
    var repoName:String,
    var repoDesc:String?,
    var repoLink:String
): Parcelable