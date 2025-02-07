package com.example.pieski.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteDogResponse(
    val message: String,
    val status: String
)

