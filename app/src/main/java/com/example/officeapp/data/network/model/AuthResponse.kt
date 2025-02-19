package com.example.officeapp.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse (
    val response: Response
)

@Serializable
data class Response(
    val token: String
)