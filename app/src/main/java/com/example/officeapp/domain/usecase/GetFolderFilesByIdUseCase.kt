package com.example.officeapp.domain.usecase

import com.example.officeapp.domain.models.FilesModel

interface GetFolderFilesByIdUseCase {
    suspend fun getFolderFilesById(authKey: String, id: Int): FilesModel
}