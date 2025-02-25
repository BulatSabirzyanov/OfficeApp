package com.example.officeapp.di.module

import androidx.lifecycle.ViewModel
import com.example.officeapp.di.ViewModelKey
import com.example.officeapp.presentation.auth.AuthViewModel
import com.example.officeapp.presentation.doc.DocumentsViewModel
import com.example.officeapp.presentation.profile.ProfileViewModel
import com.example.officeapp.presentation.rooms.RoomsViewModel
import com.example.officeapp.presentation.trash.TrashViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(DocumentsViewModel::class)
    abstract fun bindDocumentsViewModel(viewModel: DocumentsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    abstract fun bindRoomsViewModel(viewModel: RoomsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrashViewModel::class)
    abstract fun bindTrashViewModel(viewModel: TrashViewModel): ViewModel


}