package com.example.officeapp.domain.usecase

import com.example.officeapp.domain.models.ProfileModel

interface GetProfileUseCase {
    suspend fun getProfile(authKey: String): ProfileModel
}