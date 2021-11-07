package com.notchdev.githubtracker.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Parent(
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "sha")
    val sha: String,
    @Json(name = "url")
    val url: String
)