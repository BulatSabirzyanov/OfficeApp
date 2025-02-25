package com.example.officeapp.domain.usecase

import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse

interface AuthUseCase {
    suspend fun auth(authBody: AuthBody): AuthResponse
}