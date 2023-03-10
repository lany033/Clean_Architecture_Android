package com.example.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//User data class that will map the JSON REPRESENTATION OF THE USER
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String
)
