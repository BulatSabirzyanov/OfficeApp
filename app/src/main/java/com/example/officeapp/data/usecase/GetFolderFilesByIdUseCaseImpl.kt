package com.example.officeapp.data.usecase

import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.DocsRepository
import com.example.officeapp.domain.usecase.GetFolderFilesByIdUseCase
import javax.inject.Inject

class GetFolderFilesByIdUseCaseImpl @Inject constructor(
    private val repository: DocsRepository
) : GetFolderFilesByIdUseCase {
    override suspend fun getFolderFilesById(authKey: String, id: Int): FilesModel {
        return repository.getFilesById( authKey,id)
    }
}