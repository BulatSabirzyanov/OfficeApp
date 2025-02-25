package com.example.officeapp.domain.usecase

import com.example.officeapp.domain.models.FilesModel

interface GetRoomsUseCase {
    suspend fun getRooms(authKey:String): FilesModel
}