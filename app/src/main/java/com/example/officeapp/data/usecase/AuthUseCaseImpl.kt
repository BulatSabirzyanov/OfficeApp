package com.example.officeapp.data.usecase

import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse
import com.example.officeapp.domain.repository.AuthRepository
import com.example.officeapp.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : AuthUseCase {

    override suspend fun auth(authBody: AuthBody): AuthResponse {
        return repository.auth(authBody)
    }
}