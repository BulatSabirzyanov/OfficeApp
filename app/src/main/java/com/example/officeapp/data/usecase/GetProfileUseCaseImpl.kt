package com.example.officeapp.data.usecase

import com.example.officeapp.domain.models.ProfileModel
import com.example.officeapp.domain.repository.ProfileRepository
import com.example.officeapp.domain.usecase.GetProfileUseCase
import javax.inject.Inject

class GetProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : GetProfileUseCase {

    override suspend fun getProfile(authKey: String): ProfileModel {
        return profileRepository.getProfile(authKey)
    }
}