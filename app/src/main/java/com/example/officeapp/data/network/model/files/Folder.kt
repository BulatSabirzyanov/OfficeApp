package com.example.officeapp.data.network.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Folder (
    @SerialName("id") val id: Int?,
    @SerialName("title") val title: String?,
    )