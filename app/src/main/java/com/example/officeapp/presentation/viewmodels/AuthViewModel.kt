package com.example.officeapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officeapp.data.network.BaseUrlInterceptor
import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.domain.usecase.AuthUseCase
import com.example.officeapp.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val token: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val baseUrlInterceptor: BaseUrlInterceptor,
    private val useCase: AuthUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun auth(email: String, password: String) {
        viewModelScope.launch {
            _authState.update { AuthState.Loading }

            val result = runCatching {
                useCase.auth(AuthBody(email, password))
            }

            result.onSuccess { response ->
                sessionManager.saveToken(response.response.token)
                sessionManager.saveEmail(email)
                _authState.update { AuthState.Success(response.response.token) }
            }.onFailure { exception ->
                _authState.update {
                    AuthState.Error(exception.message ?: "Ошибка авторизации")
                }
            }
        }
    }

    fun updateUrl(url: String) {
        baseUrlInterceptor.updateBaseUrl(url)
    }
}