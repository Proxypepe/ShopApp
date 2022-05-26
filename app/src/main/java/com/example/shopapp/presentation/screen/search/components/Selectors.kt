package com.example.shopapp.presentation.screen.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shopapp.divideList
import com.example.shopapp.domain.filters.FilterCategory


@Composable
fun Selector(categories: List<FilterCategory>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {
            val pair = divideList(categories)
            val first = pair.first
            val second = pair.second
            for (i in 0 until first.size)
                SelectorBlock(first[i], second[i])
        }
    }
}

@Composable
fun SelectorBlock(leftCategory: FilterCategory?, rightCategory: FilterCategory?) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            if (leftCategory != null) {
                SelectorCard(leftCategory)
            }
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            if (rightCategory != null) {
                SelectorCard(rightCategory)
            }
        }
    }
}

@Composable
fun SelectorCard(category: FilterCategory) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        CustomCheckBox(
            name = category.name,
            checked = category.selected,
            onCheckedChange = {
                category.selected.value = it
            },
            onTextClicked = { category.selected.value = !category.selected.value }
        )
    }
}
