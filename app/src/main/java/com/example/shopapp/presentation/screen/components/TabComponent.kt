package com.example.shopapp.presentation.screen.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopapp.repository.remote.models.ProductDto
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun Description(text: String) {
    Text(text=text,
    modifier = Modifier.padding(20.dp).fillMaxSize()
    )
}

@Composable
fun Characteristic(product: ProductDto) {
    LazyColumn(modifier = Modifier.padding(20.dp).fillMaxSize()) {
        item {
            Row {
                Text(text = "Бранд:",
                    modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = product.brand)
            }
        }
        if(product.shell_type != null)
            item {
                 Row {
                     Text(text = "Тип корпуса:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.shell_type)
                 }
            }
        if(product.top_deck != null)
            item {
                 Row {
                     Text(text = "Верхняя дека:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.top_deck)
                 }
            }
        if(product.top_material != null)
            item {
                 Row {
                     Text(text = "Материал топа:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.top_material)
                 }
            }
        if(product.back_deck != null)
            item {
                 Row {
                     Text(text = "Задняя дека:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                     Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.back_deck)
                 }
            }
        if(product.neck_material != null)
            item {
                 Row {
                     Text(text = "Материал грифа:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.neck_material)
                 }
            }
        if(product.overlay != null)
            item {
                 Row {
                     Text(text = "Накладка:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.overlay)
                 }
            }
        if(product.strings != null)
            item {
                 Row {
                     Text(text = "Количество струн:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.strings)
                 }
            }
        if(product.neck_attachment != null)
            item {
                 Row {
                     Text(text = "Крепление грифа:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.neck_attachment)
                 }
            }
        if(product.mensura != null)
            item {
                 Row {
                     Text(text = "Мензура:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.mensura)
                 }
            }
        if(product.neck_width != null)
            item {
                 Row {
                     Text(text = "Ширина грифа:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.neck_width)
                 }
            }
        if(product.color != null)
            item {
                 Row {
                     Text(text = "Цвет:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.color)
                 }
            }
        if(product.tailpiece != null)
            item {
                 Row {
                     Text(text = "Струнодержатель:",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.tailpiece)
                 }
            }
        if(product.produced != null)
            item {
                Row {
                    Text(text = "Произведено:",
                        modifier = Modifier.fillMaxWidth(0.6f))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = product.produced)
                }
            }
        if(product.cutout != null)
            item {
                Row {
                    Text(text = "Вырез",
                        modifier = Modifier.fillMaxWidth(0.6f))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = product.cutout)
                }
            }
        if(product.varnish != null)
            item {
                 Row {
                     Text(text = "Лак",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.varnish)
                 }
            }
        if(product.form != null)
            item {
                 Row {
                     Text(text = "Форма",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.form)
                 }
            }
        if(product.specials != null)
            item {
                 Row {
                     Text(text = "Особенности",
                         modifier = Modifier.fillMaxWidth(0.6f))
                Spacer(modifier = Modifier.width(5.dp))
                     Text(text = product.specials)
                 }
            }
        if(product.lads != null)
            item {
                Row {
                    Text(text = "Количество ладов",
                        modifier = Modifier.fillMaxWidth(0.6f))
                    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
                    Text(text = product.lads)
                }
            }
    }
}


@ExperimentalPagerApi
@Composable
fun TabScreen(productDto: ProductDto) {
    val pagerState = rememberPagerState(pageCount = 2)
    Column {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, productDto)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("Описание", "Характеристики")
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            TabRowDefaults.Divider(
                thickness = 2.dp,
                color = Color.White
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.Green
            )
        }
    ) {
        list.forEachIndexed { index, _->
            Tab(
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, product: ProductDto) {
    HorizontalPager(state = pagerState, modifier = Modifier.padding(20.dp).fillMaxWidth().height(290.dp)) { page ->
        when(page) {
            0 -> Description("Hello")
            1 -> Characteristic(product)
        }
    }
}