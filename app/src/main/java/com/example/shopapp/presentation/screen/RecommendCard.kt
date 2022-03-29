package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.R

// model {name}, nav
@Composable
fun RecommendCard(id: Int){
    Card(
        modifier = Modifier.size(140.dp, 200.dp).padding(end=10.dp),
        elevation = 5.dp
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = ""
            )
            Text(
                text="Cost", fontWeight = FontWeight.Bold
            )
            Text(
                text="Name $id", maxLines = 2, overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecommendCardPreview(){
    RecommendCard(10)
}