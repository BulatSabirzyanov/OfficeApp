package com.example.officeapp.data.network.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilesResponse(
    @SerialName("count") val count: Int?,
    @SerialName("response") val response: Response?,
    @SerialName("status") val status: Int?,
    @SerialName("statusCode") val statusCode: Int?
)