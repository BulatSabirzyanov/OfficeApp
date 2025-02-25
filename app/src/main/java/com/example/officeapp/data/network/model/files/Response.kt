package com.example.officeapp.data.network.model.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
    @SerialName("count") val count: Int?,
    @SerialName("files") val files: List<File>?,
    @SerialName("folders") val folders: List<Folder>?,
    @SerialName("new") val new: Int?,
    @SerialName("startIndex") val startIndex: Int?,
    @SerialName("total") val total: Int?
)