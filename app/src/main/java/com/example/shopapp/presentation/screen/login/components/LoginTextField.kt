package com.example.shopapp.presentation.screen.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.theme.FontSize


@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        label =  {
            Text(
                text = label,
                fontSize = FontSize.Little.size
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White),
        placeholder = {
            Text (
                placeholder
            )
        },
        shape = RoundedCornerShape(8.dp)
    )
}
