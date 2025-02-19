package com.example.officeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.officeapp.App
import com.example.officeapp.presentation.viewmodels.ViewModelFactory
import com.example.officeapp.ui.theme.OfficeAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инжекция зависимостей
        (application as App).appComponent.inject(this)

        setContent {
            OfficeAppTheme {
                val navController = rememberNavController()

                Box(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(viewModelFactory, navController)
                }
            }
        }
    }
}
