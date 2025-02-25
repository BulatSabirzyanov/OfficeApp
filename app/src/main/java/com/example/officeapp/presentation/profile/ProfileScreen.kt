package com.example.officeapp.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.officeapp.R

@Composable
fun ProfileScreen(
    viewModelFactory: ViewModelProvider.Factory,
    onNavigate: () -> Unit
) {
    val viewModel: ProfileViewModel = viewModel(factory = viewModelFactory)
    val profileState by viewModel.profileState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 36.sp),
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp, top = 100.dp)
                .align(Alignment.Start)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.account_circle_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 24.dp)
            )
            Text(
                text = profileState?.let { "${it.firstName ?: "Unknown"} ${it.lastName ?: "User"}" }
                    ?: "User Name",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 28.sp),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "E-mail",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
            )
            Text(
                text = profileState?.email ?: "Email украли",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    viewModel.logout()
                    onNavigate.invoke()
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Logout")
            }
        }
    }
}