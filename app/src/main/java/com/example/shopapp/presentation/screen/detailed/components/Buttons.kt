package com.example.shopapp.presentation.screen.detailed.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.theme.AppTheme


@Composable
fun WriterTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
){
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = placeholder,
                style = AppTheme.typography.subtitle2,
                color = AppTheme.textColors.secondaryTextColor
            )
        },
        shape = RoundedCornerShape(8.dp)
    )
}
