package com.example.officeapp.presentation.doc

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.officeapp.R

@Composable
fun DocumentsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
    onDocumentClick: (String, String) -> Unit
) {
    val viewModel: DocumentsViewModel = viewModel(factory = viewModelFactory)
    val filesState by viewModel.filesState.collectAsState()

    LaunchedEffect(Unit) {
        val authKey = viewModel.getAuthKey()
        if (authKey.isNotEmpty()) {
            viewModel.getAllFiles(authKey)
        } else {
            navController.navigate("auth")
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            "Documents",
            modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 36.sp
            )
        )

        filesState?.let { filesModel ->
            LazyColumn {
                filesModel.folders?.let { folders ->
                    items(folders) { folder ->
                        DocumentItem(
                            text = folder.title ?: "Unnamed Folder",
                            iconInt = R.drawable.folder_icon,
                            onDocumentClick = {
                                folder.folderId.let { id ->
                                    Log.d("DocumentsScreen", "Navigating to folder: $id")
                                    onDocumentClick(
                                        id.toString(),
                                        folder.title ?: "Unnamed Folder"
                                    )
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
fun DocumentDetailScreen(
    navController: NavHostController,
    docId: String?,
    folderTitle: String?,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: DocumentsViewModel = viewModel(factory = viewModelFactory)
    val folderContentsState by viewModel.folderContentsState.collectAsState()

    LaunchedEffect(docId) {
        docId?.toIntOrNull()?.let { folderId ->
            val authKey = viewModel.getAuthKey()
            if (authKey.isNotEmpty()) {
                Log.d("DocumentDetailScreen", "Fetching contents for folder: $folderId")
                viewModel.getFolderContents(authKey, folderId)
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
            text = folderTitle?.let { java.net.URLDecoder.decode(it, "UTF-8") } ?: "Unnamed Folder",
            modifier = Modifier.padding(start = 16.dp, top = 54.dp, bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 36.sp
            )
        )

        folderContentsState?.let { contents ->
            LazyColumn {
                contents.folders?.let { subFolders ->
                    items(subFolders) { folder ->
                        DocumentItem(
                            text = folder.title ?: "Unnamed Folder",
                            iconInt = R.drawable.folder_icon,
                            onDocumentClick = {
                                folder.folderId.let { id ->
                                    navController.navigate("documentDetail/$id?title=${folder.title ?: "Unnamed Folder"}")
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
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun DocumentItem(
    text: String,
    iconInt: Int,
    onDocumentClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onDocumentClick(text) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(iconInt),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.secondary
    )
}
