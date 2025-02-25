package com.example.officeapp.presentation.trash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.officeapp.domain.models.FilesModel
import com.example.officeapp.domain.usecase.GetFolderFilesByIdUseCase
import com.example.officeapp.domain.usecase.GetTrashUseCase
import com.example.officeapp.utils.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class TrashViewModel @Inject constructor(
    private val getTrashUseCase: GetTrashUseCase,
    private val getFolderFilesByIdUseCase: GetFolderFilesByIdUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _trashState = MutableStateFlow<FilesModel?>(null)
    val trashState: StateFlow<FilesModel?> = _trashState

    sealed class TrashContentsState {
        data class Success(val contents: FilesModel) : TrashContentsState()
        object Forbidden : TrashContentsState()
        object Loading : TrashContentsState()
        object Error : TrashContentsState()
    }

    private val _trashContentsState =
        MutableStateFlow<TrashContentsState>(TrashContentsState.Loading)
    val trashContentsState: StateFlow<TrashContentsState> = _trashContentsState

    fun getAllTrash(authKey: String) {
        viewModelScope.launch {
            try {
                val trash = getTrashUseCase.getTrash(authKey)
                _trashState.value = trash
            } catch (e: Exception) {
                _trashState.value = null
            }
        }
    }

    fun getTrashFolderContents(authKey: String, folderId: Int) {
        viewModelScope.launch {
            _trashContentsState.value = TrashContentsState.Loading
            try {
                val contents = getFolderFilesByIdUseCase.getFolderFilesById(authKey, folderId)
                _trashContentsState.value = TrashContentsState.Success(contents)
            } catch (e: HttpException) {

                if (e.code() == 403) {
                    _trashContentsState.value = TrashContentsState.Forbidden
                } else {
                    _trashContentsState.value = TrashContentsState.Error
                }
            } catch (e: Exception) {
                _trashContentsState.value = TrashContentsState.Error
            }
        }
    }

    fun getAuthKey(): String {
        return sessionManager.getToken() ?: ""
    }
}