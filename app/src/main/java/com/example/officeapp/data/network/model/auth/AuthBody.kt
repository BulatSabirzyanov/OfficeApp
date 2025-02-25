package com.example.officeapp.data.network.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthBody (
    @SerialName("userName")
    val userName: String,
    @SerialName("password")
    val password: String
)