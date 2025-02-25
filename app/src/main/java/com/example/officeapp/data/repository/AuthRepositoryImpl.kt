package com.example.officeapp.data.repository

import com.example.officeapp.data.mapper.FileDataToDomainMapper
import com.example.officeapp.data.network.OnlyOfficeApiService
import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val onlyOfficeApiService: OnlyOfficeApiService,
    private val fileMapper: FileDataToDomainMapper
): AuthRepository{
    override suspend fun auth(authBody: AuthBody): AuthResponse {
        return onlyOfficeApiService.auth(authBody)
    }

    override suspend fun getAllFiles(authKey: String): FilesModel {
        val filesResponse = onlyOfficeApiService.getMyFiles("asc_auth_key=$authKey")
        return fileMapper.map(filesResponse)
    }

    override suspend fun getFilesById(authKey: String, id: Int): FilesModel {
        val filesResponse = onlyOfficeApiService.getFolderFilesById("asc_auth_key=$authKey", id)
        return fileMapper.map(filesResponse)
    }
}