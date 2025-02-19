package com.example.officeapp.data.repository

import com.example.officeapp.data.network.OnlyOfficeApiService
import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.data.network.model.AuthResponse
import com.example.officeapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val onlyOfficeApiService: OnlyOfficeApiService
): AuthRepository{
    override suspend fun auth(authBody: AuthBody): AuthResponse {
        return onlyOfficeApiService.auth(authBody)
    }
}