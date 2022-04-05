package com.example.shopapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadProgress(
    isDisplayed: Boolean,
) {
    if (isDisplayed) {
        Row (modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
            horizontalArrangement = Arrangement.Center
        ){
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}