package com.example.officeapp.domain.usecase

import com.example.officeapp.domain.models.FilesModel

interface GetAllFilesUseCase {
    suspend fun getAllFiles(authKey: String): FilesModel
}