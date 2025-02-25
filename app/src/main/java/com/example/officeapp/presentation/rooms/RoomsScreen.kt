package com.example.officeapp.presentation.rooms

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun RoomsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController
) {
    val viewModel: RoomsViewModel = viewModel(factory = viewModelFactory)
    val roomsState by viewModel.roomsState.collectAsState()

    LaunchedEffect(Unit) {
        val authKey = viewModel.getAuthKey()
        if (authKey.isNotEmpty()) {
            viewModel.getAllRooms(authKey)
        } else {
            navController.navigate("auth")
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            text = "Rooms",
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp),
            modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp)
        )

        roomsState?.let { filesModel ->
            LazyColumn {
                filesModel.folders?.let { rooms ->
                    items(rooms) { room ->
                        RoomItem(
                            text = room.title ?: "Unnamed Room",
                            onRoomClick = {
                                // Здесь можно добавить навигацию, если нужно
                            }
                        )
                    }
                }
                filesModel.files?.let { files ->
                    items(files) { file ->
                        RoomItem(
                            text = file.title ?: "Unnamed File",
                            onRoomClick = { /* Обработка клика по файлу */ }
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
fun RoomItem(
    text: String,
    onRoomClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onRoomClick(text) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val words = text.split(" ").filter { it.isNotEmpty() }
        val initials = when {
            words.size >= 2 -> "${words[0].firstOrNull()?.uppercase() ?: ""}${
                words[1].firstOrNull()?.uppercase() ?: ""
            }"

            words.isNotEmpty() -> words[0].firstOrNull()?.uppercase() ?: ""
            else -> "UR"
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.secondary)
}