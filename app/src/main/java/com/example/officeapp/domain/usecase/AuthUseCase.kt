package com.example.officeapp.domain.usecase

import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.data.network.model.AuthResponse

interface AuthUseCase {

    suspend fun auth(authBody: AuthBody):AuthResponse
}