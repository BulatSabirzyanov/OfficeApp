package com.example.officeapp.domain.repository

import com.example.officeapp.domain.models.ProfileModel

interface ProfileRepository {
    suspend fun getProfile(authKey: String): ProfileModel
}