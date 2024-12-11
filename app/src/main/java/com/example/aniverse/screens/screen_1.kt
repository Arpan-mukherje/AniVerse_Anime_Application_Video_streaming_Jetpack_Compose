package com.example.aniverse.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aniverse.viewmodel.TestViewModel
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen1(viewModel: TestViewModel = hiltViewModel()) {
    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center

            ) {
                Column {

                    Text(text = "Screen 1")
                    viewModel.list2.forEach { item ->
                        Text(text = item)
                    }
                }
            }
        }
    )
}