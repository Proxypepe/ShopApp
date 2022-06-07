package com.example.shopapp.presentation.screen.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.theme.AppTheme

@Composable
fun CustomCheckBox(
    name: String,
    checked: MutableState<Boolean>,
    onCheckedChange: ((Boolean) -> Unit),
    onTextClicked: (() -> Unit)
) {
    // FIXME: RENAME FUNCTION
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { onCheckedChange(it) }
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = name,
            modifier = Modifier.clickable {
                onTextClicked()
            }, color = AppTheme.textColors.primaryTextColor
        )
    }
}
