package com.example.officeapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("response") val response: Response?,
    @SerialName("status") val status: Int?,
    @SerialName("statusCode") val statusCode: Int?
)

@Serializable
data class Response(
    @SerialName("email") val email: String?,
    @SerialName("firstName") val firstName: String?,
    @SerialName("lastName") val lastName: String?,
    @SerialName("avatar") val avatar: String?,
)