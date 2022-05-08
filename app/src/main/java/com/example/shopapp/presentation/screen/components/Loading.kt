package com.example.shopapp.presentation.screen.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.theme.AppTheme

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
                color = AppTheme.colors.primary
            )
        }
    }
}