package com.example.officeapp.domain.repository

import com.example.officeapp.domain.models.FilesModel

interface DocsRepository {

    suspend fun getAllFiles(authKey: String): FilesModel

    suspend fun getFilesById(authKey: String, id: Int): FilesModel

    suspend fun getRooms(authKey: String): FilesModel

    suspend fun getTrash(authKey: String): FilesModel
}