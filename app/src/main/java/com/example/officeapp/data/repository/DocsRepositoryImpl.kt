package com.example.officeapp.data.repository

import com.example.officeapp.data.mappers.FileDataToDomainMapper
import com.example.officeapp.data.network.OnlyOfficeApiService
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.DocsRepository
import javax.inject.Inject

class DocsRepositoryImpl @Inject constructor(
    private val onlyOfficeApiService: OnlyOfficeApiService,
    private val fileMapper: FileDataToDomainMapper
) : DocsRepository {
    override suspend fun getAllFiles(authKey: String): FilesModel {
        val filesResponse = onlyOfficeApiService.getMyFiles("asc_auth_key=$authKey")
        return fileMapper.map(filesResponse)
    }

    override suspend fun getFilesById(authKey: String, id: Int): FilesModel {
        val filesResponse = onlyOfficeApiService.getFolderFilesById("asc_auth_key=$authKey", id)
        return fileMapper.map(filesResponse)
    }

    override suspend fun getRooms(authKey: String): FilesModel {
        val filesResponse = onlyOfficeApiService.getRooms("asc_auth_key=$authKey")
        return fileMapper.map(filesResponse)
    }

    override suspend fun getTrash(authKey: String): FilesModel {
        val filesResponse = onlyOfficeApiService.getTrash("asc_auth_key=$authKey")
        return fileMapper.map(filesResponse)
    }
}