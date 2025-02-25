package com.example.officeapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officeapp.domain.models.ProfileModel
import com.example.officeapp.domain.usecase.GetProfileUseCase
import com.example.officeapp.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val getProfileUseCase: GetProfileUseCase // Переименовал для соответствия вашему коду
) : ViewModel() {

    private val _profileState = MutableStateFlow<ProfileModel?>(null)
    val profileState: StateFlow<ProfileModel?> = _profileState

    init {
        fetchProfile() // Запускаем загрузку данных профиля при создании ViewModel
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            try {
                val authKey = sessionManager.getToken() ?: return@launch
                val profile = getProfileUseCase.getProfile(authKey)
                _profileState.value = profile
            } catch (e: Exception) {
                _profileState.value = null
            }
        }
    }

    fun logout() {
        sessionManager.clearSession()
    }
}