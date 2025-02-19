package com.example.officeapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.officeapp.R
import com.example.officeapp.presentation.viewmodels.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigate: () -> Unit
) {

    val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)

    val email by rememberSaveable { mutableStateOf(viewModel.getEmail()) }


    Column {
        Text(
            "Profile",
            modifier = Modifier.padding(start = 16.dp, top = 100.dp, bottom = 16.dp),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 36.sp
            )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.account_circle_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 48.dp)
                    .size(180.dp)
            )
            Text(
                "User Name", style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 28.sp
                ),
                modifier = Modifier.padding(bottom = 36.dp)
            )
            Text(
                "E-mail", style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 20.sp
                )
            )
            Text(email ?: "Email украли")
            Button(onClick = { onNavigate.invoke() }) { Text("Logout")
                LaunchedEffect(Unit) {
                    viewModel.logout()
                }
            }
        }

    }


}