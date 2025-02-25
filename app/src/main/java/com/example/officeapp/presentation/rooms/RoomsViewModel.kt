package com.example.officeapp.presentation.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.usecase.GetRoomsUseCase
import com.example.officeapp.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _roomsState = MutableStateFlow<FilesModel?>(null)
    val roomsState: StateFlow<FilesModel?> = _roomsState

    fun getAllRooms(authKey: String) {
        viewModelScope.launch {
            try {
                val rooms = getRoomsUseCase.getRooms(authKey)
                _roomsState.value = rooms
            } catch (e: Exception) {
                _roomsState.value = null
            }
        }
    }

    fun getAuthKey(): String {
        return sessionManager.getToken() ?: ""
    }
}