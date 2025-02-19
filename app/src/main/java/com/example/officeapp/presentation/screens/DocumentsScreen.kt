package com.example.officeapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.officeapp.R

@Composable
fun DocumentsScreen(onDocumentClick: (String) -> Unit) {
    val documents = listOf(
        "New Folder" to R.drawable.folder_icon,
        "Sample Document.docx" to R.drawable.menu_icon
    )

    Column(Modifier.fillMaxSize()) {
        Text(
            "Documents",
            modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp)
        )
        documents.forEach { doc ->
            DocumentItem(doc.first, doc.second) {
                onDocumentClick(doc.first)
            }
        }
    }
}

@Composable
fun DocumentDetailScreen(navController: NavHostController, docId: String?) {
    Column {

        Icon(
            painter = painterResource(R.drawable.back_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .clickable {
                    navController.navigate("documents")
                }
        )

        Text(
            text = "$docId",
            modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp)
        )

        DocumentItem("New document.docx", R.drawable.menu_icon) {}
    }
}

@Composable
fun DocumentItem(
    text: String,
    iconInt: Int,
    onDocumentClick: (String) -> Unit
) {
    Row {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable { if (text == "New Folder") onDocumentClick(text) }
            ) {
                Icon(
                    painter = painterResource(iconInt),
                    contentDescription = null
                )
                Text(text, modifier = Modifier.padding(start = 8.dp))
            }
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.secondary)
        }
    }
}
