package com.example.officeapp.data.repository

import com.example.officeapp.data.mappers.ProfileDataToDomainMapper
import com.example.officeapp.data.network.OnlyOfficeApiService
import com.example.officeapp.domain.models.ProfileModel
import com.example.officeapp.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val officeApi: OnlyOfficeApiService, // Интерфейс API с методом getProfile
    private val profileMapper: ProfileDataToDomainMapper
) : ProfileRepository {

    override suspend fun getProfile(authKey: String): ProfileModel {
        val profileResponse = officeApi.getMyProfile(authKey = "asc_auth_key=$authKey")
        return profileMapper.map(profileResponse)
    }
}