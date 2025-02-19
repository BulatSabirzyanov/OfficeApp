package com.example.officeapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrashScreen(
) {
    Text(
        "Trash",
        modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp),
        style = MaterialTheme.typography.labelLarge.copy(
            fontSize = 36.sp
        )
    )
}