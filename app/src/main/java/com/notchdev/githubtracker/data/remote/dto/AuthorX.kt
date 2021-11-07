package com.notchdev.githubtracker.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorX(
    @Json(name = "date")
    val date: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "name")
    val name: String
)