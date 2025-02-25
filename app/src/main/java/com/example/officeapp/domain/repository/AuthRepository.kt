package com.example.officeapp.domain.repository

import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse

interface AuthRepository {

    suspend fun auth(authBody: AuthBody): AuthResponse

}