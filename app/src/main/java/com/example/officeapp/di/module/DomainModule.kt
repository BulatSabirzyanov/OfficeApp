package com.example.officeapp.di.module

import com.example.officeapp.data.repository.AuthRepositoryImpl
import com.example.officeapp.data.repository.DocsRepositoryImpl
import com.example.officeapp.data.repository.ProfileRepositoryImpl
import com.example.officeapp.data.usecase.AuthUseCaseImpl
import com.example.officeapp.data.usecase.GetAllFilesUseCaseImpl
import com.example.officeapp.data.usecase.GetFolderFilesByIdUseCaseImpl
import com.example.officeapp.data.usecase.GetProfileUseCaseImpl
import com.example.officeapp.data.usecase.GetRoomsUseCaseImpl
import com.example.officeapp.data.usecase.GetTrashUseCaseImpl
import com.example.officeapp.domain.repository.AuthRepository
import com.example.officeapp.domain.repository.DocsRepository
import com.example.officeapp.domain.repository.ProfileRepository
import com.example.officeapp.domain.usecase.AuthUseCase
import com.example.officeapp.domain.usecase.GetAllFilesUseCase
import com.example.officeapp.domain.usecase.GetFolderFilesByIdUseCase
import com.example.officeapp.domain.usecase.GetProfileUseCase
import com.example.officeapp.domain.usecase.GetRoomsUseCase
import com.example.officeapp.domain.usecase.GetTrashUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindAuthRepository(
        repositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindProfileRepository(
        repositoryImpl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    fun bindDocsRepository(
        repositoryImpl: DocsRepositoryImpl
    ): DocsRepository

    @Binds
    fun  bindAuthUseCase(
        authUseCaseImpl: AuthUseCaseImpl
    ): AuthUseCase

    @Binds
    fun  bindGetAllFilesUseCase(
        getAllFilesUseCaseImpl: GetAllFilesUseCaseImpl
    ): GetAllFilesUseCase

    @Binds
    fun  bindGetFolderFilesByIdUseCase(
        getFolderFilesByIdUseCaseImpl: GetFolderFilesByIdUseCaseImpl
    ): GetFolderFilesByIdUseCase

    @Binds
    fun bindGetRoomsUseCase(
        getGetRoomsUseCaseImpl: GetRoomsUseCaseImpl
    ): GetRoomsUseCase

    @Binds
    fun bindGetTrashUseCase(
        getGetTrashUseCaseImpl: GetTrashUseCaseImpl
    ): GetTrashUseCase


    @Binds
    fun bindGetProfileUseCase(
        getProfileUseCaseImpl: GetProfileUseCaseImpl
    ): GetProfileUseCase
}