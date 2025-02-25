package com.example.officeapp.data.network

import com.example.officeapp.data.network.model.ProfileResponse
import com.example.officeapp.data.network.model.auth.AuthBody
import com.example.officeapp.data.network.model.auth.AuthResponse
import com.example.officeapp.data.network.model.files.FilesResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface OnlyOfficeApiService {

    @POST("api/2.0/authentication")
    suspend fun auth(@Body body: AuthBody): AuthResponse

    @GET("/api/2.0/files/@my")
    suspend fun getMyFiles(@Header("Cookie") authKey: String) : FilesResponse

    @GET("/api/2.0/files/{id}")
    suspend fun getFolderFilesById(
        @Header("Cookie") authKey: String,
        @Path("id") id: Int,
    ): FilesResponse

    @GET("/api/2.0/files/rooms")
    suspend fun getRooms(
        @Header("Cookie") authKey: String
    ): FilesResponse

    @GET("/api/2.0/files/@trash")
    suspend fun getTrash(
        @Header("Cookie") authKey: String
    ): FilesResponse

    @GET("/api/2.0/people/@self")
    suspend fun getMyProfile(
        @Header("Cookie") authKey: String
    ): ProfileResponse
}