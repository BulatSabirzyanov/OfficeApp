package com.example.officeapp.data.network

import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.data.network.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OnlyOfficeApiService {

    @POST("api/2.0/authentication")
    suspend fun auth(@Body body: AuthBody): AuthResponse

}