package com.example.shopapp.presentation.screen.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shopapp.R

@Composable
fun BrandCard(){
    Card(
        modifier = Modifier
            .size(140.dp, 200.dp)
            .padding(end = 10.dp),
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(R.drawable.ic_android_black_24dp),
                contentDescription = "",
            )
            Text(text="Brand",
                modifier = Modifier.fillMaxWidth())
        }
    }
}