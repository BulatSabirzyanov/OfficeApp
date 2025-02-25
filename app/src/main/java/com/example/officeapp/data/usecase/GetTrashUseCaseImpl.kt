package com.example.officeapp.data.usecase

import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.DocsRepository
import com.example.officeapp.domain.usecase.GetTrashUseCase
import javax.inject.Inject

class GetTrashUseCaseImpl @Inject constructor(
    private val repository: DocsRepository
) : GetTrashUseCase {
    override suspend fun getTrash(authKey: String): FilesModel {
        return repository.getTrash(authKey)
    }
}