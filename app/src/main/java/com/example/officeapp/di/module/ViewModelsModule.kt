package com.example.officeapp.di.module

import androidx.lifecycle.ViewModel
import com.example.officeapp.di.ViewModelKey
import com.example.officeapp.presentation.viewmodels.AuthViewModel
import com.example.officeapp.presentation.viewmodels.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}