package com.example.officeapp.data.network.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class File (
    @SerialName("title") val title: String?,
)