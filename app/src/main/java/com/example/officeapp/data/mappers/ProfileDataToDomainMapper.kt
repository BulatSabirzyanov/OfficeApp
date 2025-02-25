package com.example.officeapp.data.mappers

import com.example.officeapp.data.network.model.ProfileResponse
import com.example.officeapp.domain.models.ProfileModel
import javax.inject.Inject

class ProfileDataToDomainMapper @Inject constructor() {

    fun map(profileResponse: ProfileResponse): ProfileModel {
        val response = profileResponse.response
        return ProfileModel(
            email = response?.email,
            firstName = response?.firstName,
            lastName = response?.lastName,
            avatar = response?.avatar
        )
    }
}