package com.example.officeapp.di.module

import com.example.officeapp.data.repository.AuthRepositoryImpl
import com.example.officeapp.data.usecase.AuthUseCaseImpl
import com.example.officeapp.data.usecase.GetAllFilesUseCaseImpl
import com.example.officeapp.data.usecase.GetFolderFilesByIdUseCaseImpl
import com.example.officeapp.domain.repository.AuthRepository
import com.example.officeapp.domain.usecase.AuthUseCase
import com.example.officeapp.domain.usecase.GetAllFilesUseCase
import com.example.officeapp.domain.usecase.GetFolderFilesByIdUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindAuthRepository(
        repositoryImpl: AuthRepositoryImpl
    ): AuthRepository

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
}