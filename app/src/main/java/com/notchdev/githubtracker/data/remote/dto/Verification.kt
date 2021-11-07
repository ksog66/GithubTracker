package com.notchdev.githubtracker.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Verification(
    @Json(name = "payload")
    val payload: Any?,
    @Json(name = "reason")
    val reason: String,
    @Json(name = "signature")
    val signature: Any?,
    @Json(name = "verified")
    val verified: Boolean
)