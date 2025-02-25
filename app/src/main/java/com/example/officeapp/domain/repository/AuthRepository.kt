package com.example.officeapp.domain.repository

import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse
import com.example.officeapp.domain.models.FilesModel

interface AuthRepository {

    suspend fun auth(authBody: AuthBody): AuthResponse

    suspend fun getAllFiles(authKey: String): FilesModel

    suspend fun getFilesById(authKey: String, id: Int): FilesModel
}