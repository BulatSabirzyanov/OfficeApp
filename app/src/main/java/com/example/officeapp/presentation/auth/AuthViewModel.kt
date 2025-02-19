package com.example.officeapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.officeapp.data.network.BaseUrlInterceptor
import com.example.officeapp.data.network.model.AuthBody
import com.example.officeapp.domain.usecase.AuthUseCase
import com.example.officeapp.presentation.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState() // Начальное состояние
    object Loading : AuthState() // Загрузка
    data class Success(val token: String) : AuthState() // Успешная аутентификация
    data class Error(val message: String) : AuthState() // Ошибка
}

class AuthViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val baseUrlInterceptor: BaseUrlInterceptor,
    private val useCase: AuthUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    var token: String? = sessionManager.getToken()
        private set

    fun auth(email: String, password: String, navController: NavHostController) {
        viewModelScope.launch {
            _authState.update {AuthState.Loading}
            try {
                val newToken = useCase.auth(AuthBody(email, password))
                sessionManager.saveToken(newToken.response.token)
                token = newToken.response.token
                _authState.update{AuthState.Success(newToken.response.token)}
            } catch (e: Exception) {
                _authState.update{AuthState.Error(e.message ?: "Ошибка авторизации")}
            }
        }
    }

    fun updateUrl(url: String) {
        baseUrlInterceptor.updateBaseUrl(url)
    }

    fun logout(navController: NavHostController) {
        sessionManager.clearSession()
        token = null
        _authState.update {AuthState.Idle}
        navController.navigate("login") {
            popUpTo("documents") { inclusive = true }
        }
    }
}