package com.example.officeapp.data.usecase

import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.repository.DocsRepository
import com.example.officeapp.domain.usecase.GetRoomsUseCase
import javax.inject.Inject

class GetRoomsUseCaseImpl @Inject constructor(
    private val repository: DocsRepository
) : GetRoomsUseCase {
    override suspend fun getRooms(authKey: String): FilesModel {
        return repository.getRooms(authKey)
    }

}