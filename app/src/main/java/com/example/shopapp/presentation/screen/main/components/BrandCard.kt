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
import com.example.shopapp.ui.theme.AppTheme

@Composable
fun BrandCard(brandInfo: BrandInfo) {
    Card(
        modifier = Modifier
            .size(200.dp, 100.dp)
            .padding(end = 10.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().height(70.dp),
                painter = painterResource(brandInfo.pic_id),
                contentDescription = "",
            )
            Text(
                text = brandInfo.brand_name,
                style = AppTheme.typography.h5,
                color = AppTheme.textColors.primaryTextColor
            )
        }
    }
}