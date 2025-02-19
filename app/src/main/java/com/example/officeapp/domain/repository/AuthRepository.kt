package com.example.officeapp.domain.repository

import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.data.network.model.AuthResponse

interface AuthRepository {

    suspend fun auth(authBody: AuthBody):AuthResponse
}