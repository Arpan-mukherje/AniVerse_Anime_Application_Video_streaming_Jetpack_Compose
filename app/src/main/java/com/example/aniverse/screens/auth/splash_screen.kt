package com.example.aniverse.screens.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.ai.client.generativeai.type.content

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController,) {
//    val splashTimeOut = viewModel.splashTimeOut.collectAsState()
//
//    Log.d("SplashScreen", "Splash timeout value: ${splashTimeOut.value}")
//
//    if (splashTimeOut.value) {
//        navController.navigate("homepage") {
//            popUpTo("splash_screen") { inclusive = true }
//        }
//    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text("Splash Screen", style = MaterialTheme.typography.headlineLarge)
            }
        }
    )
}