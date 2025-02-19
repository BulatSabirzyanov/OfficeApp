package com.example.officeapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.officeapp.utils.SessionManager
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun logout() {
        sessionManager.clearSession()
        sessionManager.clearEmail()
    }

    fun getEmail(): String? {
        return sessionManager.getEmail()
    }
}