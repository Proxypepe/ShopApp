package com.example.shopapp.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.R
import com.example.shopapp.domain.MainViewModel


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



@Composable
fun MainPage(mainPageViewModel: MainViewModel?, navController: NavHostController) {

    val products = mainPageViewModel?.recommendedProducts?.collectAsState(initial = emptyList())?.value

    val loading = mainPageViewModel?.loading?.collectAsState(false)?.value

    Box(
        modifier = Modifier.padding(10.dp)
    ){
        LazyColumn {
            //TODO: use box and padding in box
            item {
                Spacer(modifier = Modifier.padding(top=20.dp, start=25.dp))
                Row {
                    Text(
                        text = "Your Feed", fontWeight = FontWeight.Bold,
                    )
                    //FIXME: adapt padding
                    Spacer(modifier = Modifier.padding(end=100.dp))
                    //TODO: Set notifier button
                    Text(
                        text = "Some Text"
                    )
                }
                Spacer(modifier=Modifier.padding(top=5.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(R.drawable.ic_android_black_24dp),
                        contentDescription = "",
                    )
                }
                Spacer(modifier = Modifier.padding(top=10.dp))
                val values: List<Int> = listOf(1, 2, 3, 4)
                Text(text="Ваши рекомендации", fontWeight = FontWeight.Bold)
                Text(text="Основано на вашем поиске",
                    fontSize = 12.sp)

                Spacer(modifier = Modifier.padding(top=10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    if (products != null)
                        items(products) { value ->
                            RecommendCard(value, navController)
                        } else {
                        item {
                            if (loading != null) {
                                LoadProgress(loading)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top=10.dp))
                Text(text="Популярные бренды", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(top=10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(values) {
                        BrandCard()
                    }
                }
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun BrandCardPreview(){
    BrandCard()
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    val navController = rememberNavController()
    MainPage(null, navController)
}