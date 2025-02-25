package com.example.officeapp.presentation.trash

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.officeapp.R
import com.example.officeapp.presentation.doc.DocumentItem
import java.net.URLDecoder

@Composable
fun TrashScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
    onTrashClick: (String, String) -> Unit = { _, _ -> }
) {
    val viewModel: TrashViewModel = viewModel(factory = viewModelFactory)
    val trashState by viewModel.trashState.collectAsState()

    LaunchedEffect(Unit) {
        val authKey = viewModel.getAuthKey()
        if (authKey.isNotEmpty()) {
            viewModel.getAllTrash(authKey)
        } else {
            navController.navigate("auth")
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Trash",
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp),
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, top = 100.dp)
        )

        trashState?.let { filesModel ->
            LazyColumn {
                filesModel.folders?.let { folders ->
                    items(folders) { folder ->
                        DocumentItem(
                            text = folder.title ?: "Unnamed Folder",
                            iconInt = R.drawable.folder_icon,
                            onDocumentClick = {
                                folder.folderId.let { id ->
                                    Log.d("TrashScreen", "Navigating to trash folder: $id")
                                    onTrashClick(id.toString(), folder.title ?: "Unnamed Folder")
                                }
                            }
                        )
                    }
                }
                filesModel.files?.let { files ->
                    items(files) { file ->
                        DocumentItem(
                            text = file.title ?: "Unnamed File",
                            iconInt = R.drawable.menu_icon,
                            onDocumentClick = {}
                        )
                    }
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun TrashDetailScreen(
    navController: NavHostController,
    trashId: String?,
    folderTitle: String?,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: TrashViewModel = viewModel(factory = viewModelFactory)
    val trashContentsState by viewModel.trashContentsState.collectAsState()

    LaunchedEffect(trashId) {
        trashId?.toIntOrNull()?.let { folderId ->
            val authKey = viewModel.getAuthKey()
            if (authKey.isNotEmpty()) {
                Log.d("TrashDetailScreen", "Fetching contents for trash folder: $folderId")
                viewModel.getTrashFolderContents(authKey, folderId)
            } else {
                navController.navigate("auth")
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Icon(
            painter = painterResource(R.drawable.back_icon),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .size(30.dp)
                .clickable { navController.popBackStack() }
        )

        Text(
            text = folderTitle?.let { URLDecoder.decode(it, "UTF-8") } ?: "Unnamed Folder",
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp),
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, top = 100.dp)
        )

        when (trashContentsState) {
            is TrashViewModel.TrashContentsState.Success -> {
                val contents =
                    (trashContentsState as TrashViewModel.TrashContentsState.Success).contents
                LazyColumn {
                    contents.folders?.let { subFolders ->
                        items(subFolders) { folder ->
                            DocumentItem(
                                text = folder.title ?: "Unnamed Folder",
                                iconInt = R.drawable.folder_icon,
                                onDocumentClick = {
                                    folder.folderId.let { id ->
                                        navController.navigate("trashDetail/$id?title=${folder.title ?: "Unnamed Folder"}")
                                    }
                                }
                            )
                        }
                    }
                    contents.files?.let { files ->
                        items(files) { file ->
                            DocumentItem(
                                text = file.title ?: "Unnamed File",
                                iconInt = R.drawable.menu_icon,
                                onDocumentClick = {}
                            )
                        }
                    }
                }
            }

            is TrashViewModel.TrashContentsState.Forbidden -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Папка для вас пуста",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            is TrashViewModel.TrashContentsState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is TrashViewModel.TrashContentsState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Ошибка загрузки",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}