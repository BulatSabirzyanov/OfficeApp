package com.example.officeapp.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun Backpressed(context: Context,doubleBackToExitPressedOnce: MutableState<Boolean>){
    BackHandler {
        if (doubleBackToExitPressedOnce.value) {
            (context as? Activity)?.finish()
        } else {
            doubleBackToExitPressedOnce.value = true
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce.value = false
            }, 2000)
        }
    }
}