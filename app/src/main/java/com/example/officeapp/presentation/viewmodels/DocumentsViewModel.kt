package com.example.officeapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.usecase.GetAllFilesUseCase
import com.example.officeapp.domain.usecase.GetFolderFilesByIdUseCase
import com.example.officeapp.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DocumentsViewModel @Inject constructor(
    private val getAllFilesUseCase: GetAllFilesUseCase,
    private val getFolderFilesByIdUseCase: GetFolderFilesByIdUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _filesState = MutableStateFlow<FilesModel?>(null)
    val filesState: StateFlow<FilesModel?> = _filesState

    private val _folderContentsState = MutableStateFlow<FilesModel?>(null)
    val folderContentsState: StateFlow<FilesModel?> = _folderContentsState


    fun getAllFiles(authKey: String) {
        viewModelScope.launch {
            try {
                val files = getAllFilesUseCase.getAllFiles(authKey)
                _filesState.value = files
            } catch (e: Exception) {
                Log.e("DocumentsViewModel", "Error fetching files: ${e.message}")
            }
        }
    }

    fun getFolderContents(authKey: String, folderId: Int) {
        viewModelScope.launch {
            try {

                val contents = getFolderFilesByIdUseCase.getFolderFilesById(authKey, folderId)
                _folderContentsState.value = contents as FilesModel?
            } catch (e: Exception) {
                Log.e("DocumentsViewModel", "Error fetching folder contents: ${e.message}")
            }
        }
    }

    fun getAuthKey(): String {
        return try {
            sessionManager.getToken() ?: ""
        } catch (e: Exception) {
            Log.e("DocumentsViewModel", "Error fetching auth key: ${e.message}")
            ""
        }
    }
}