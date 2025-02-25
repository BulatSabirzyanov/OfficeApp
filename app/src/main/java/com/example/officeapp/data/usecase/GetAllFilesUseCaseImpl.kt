package com.example.officeapp.data.usecase

import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.DocsRepository
import com.example.officeapp.domain.usecase.GetAllFilesUseCase
import javax.inject.Inject

class GetAllFilesUseCaseImpl @Inject constructor(
    private val repository: DocsRepository
) : GetAllFilesUseCase {
    override suspend fun getAllFiles(authKey: String): FilesModel {
        return repository.getAllFiles(authKey)
    }
}