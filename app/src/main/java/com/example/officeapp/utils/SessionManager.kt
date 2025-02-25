package com.example.officeapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("user_token", token).apply()
    }

    fun saveEmail(email: String){
        prefs.edit().putString("user_email",email).apply()
    }

    fun getToken(): String? {
        return prefs.getString("user_token", null)
    }

    fun getEmail(): String? {
        return prefs.getString("user_email",null)
    }

    fun clearSession() {
        prefs.edit().remove("user_token").apply()
    }

    fun clearEmail() {
        prefs.edit().remove("user_email").apply()
    }
}