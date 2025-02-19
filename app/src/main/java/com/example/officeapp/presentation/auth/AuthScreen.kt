package com.example.officeapp.presentation.auth

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.officeapp.R

@Composable
fun AuthScreen(
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController
) {
    val viewModel: AuthViewModel = viewModel(factory = viewModelFactory)

    // Используем rememberSaveable, чтобы сохранять введённые данные
    var portal by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    // Подписка на состояние авторизации
    val authState by viewModel.authState.collectAsState()

    // Если авторизация успешна, переходим на экран документов
    if (authState is AuthState.Success) {
        LaunchedEffect(Unit) {
            navController.navigate("main") {
                popUpTo("auth") { inclusive = true } // Удаляем `AuthScreen` из стека, чтобы нельзя было вернуться назад
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Connect to ONLYOFFICE",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        EditTextCompose(
            placeholder = R.string.placeholder_portal,
            text = portal,
            onValueChanged = { portal = it },
            isEnabled = authState !is AuthState.Loading
        )

        EditTextCompose(
            placeholder = R.string.placeholder_email,
            text = email,
            onValueChanged = { email = it },
            isEnabled = authState !is AuthState.Loading
        )

        EditTextCompose(
            placeholder = R.string.placeholder_password,
            text = password,
            onValueChanged = { password = it },
            isEnabled = authState !is AuthState.Loading
        )

        ButtonProgress(
            text = R.string.placeholder_login,
            isEnabled = email.isNotEmpty() && password.isNotEmpty() && authState !is AuthState.Loading,
            isLoading = authState is AuthState.Loading,
            isError = authState is AuthState.Error,
            modifier = Modifier
                .padding(horizontal = 80.dp)
                .padding(top = 16.dp),
            onClick = {
                viewModel.updateUrl(portal)
                viewModel.auth(email, password, navController)
            }
        )

        // Отображение ошибки
        if (authState is AuthState.Error) {
            Text(
                text = (authState as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
            Log.e("123", (authState as AuthState.Error).message)
        }
    }
}



@Composable
fun EditTextCompose(
    @StringRes placeholder: Int,
    text: String,
    onValueChanged: (String) -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = { onValueChanged(it) },
        enabled = isEnabled,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_person_outline_24),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        placeholder = {
            Text(stringResource(placeholder), color = MaterialTheme.colorScheme.onPrimaryContainer)
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(16.dp)
    )
}

@Composable
fun ButtonProgress(
    @StringRes text: Int,
    isEnabled: Boolean,
    isLoading: Boolean,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val _text = if (isError) {
        stringResource(R.string.placeholder_error)
    } else {
        stringResource(text)
    }

    val colors = if (isError) {
        ButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer,
            disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
            disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    } else {
        ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    }

    Button(
        onClick = {
            onClick()
        }, modifier.fillMaxWidth(),
        enabled = isEnabled,
        colors = colors
    ) {
        if (!isLoading) {
            Text(_text)
        } else {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                trackColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        }
    }


}
