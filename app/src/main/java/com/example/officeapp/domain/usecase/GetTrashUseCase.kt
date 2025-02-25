package com.example.officeapp.domain.usecase

import com.example.officeapp.domain.models.FilesModel

interface GetTrashUseCase {
    suspend fun getTrash(authKey:String): FilesModel
}