package com.example.officeapp.data.mappers

import com.example.officeapp.data.network.model.files.FilesResponse
import com.example.officeapp.domain.models.FileModel
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.models.FolderModel
import javax.inject.Inject

class FileDataToDomainMapper @Inject constructor() {
    fun map(filesResponse: FilesResponse): FilesModel {
        val response = filesResponse.response
        val files = response?.files?.map { FileModel(title = it.title) }
        val folders = response?.folders?.map {
            FolderModel(
                folderId = it.id!!.toInt(),
                title = it.title
            )
        }
        return FilesModel(files = files, folders = folders)
    }
}