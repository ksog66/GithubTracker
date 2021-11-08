package com.notchdev.githubtracker.data.remote.dto


import com.notchdev.githubtracker.domain.model.IssuesDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class IssueResponse : ArrayList<IssueResponseItem>() {

    fun toIssueDetailList(): List<IssuesDetail> {
        return this.map { it.toIssuesDetail() }
    }
}