package com.notchdev.githubtracker.data.remote.dto


import com.notchdev.githubtracker.domain.model.CommitDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class CommitResponse : ArrayList<CommitResponseItem>() {

    fun toCommitDetailList() : List<CommitDetail> {
        return this.map { it.toCommitDetail() }
    }
}