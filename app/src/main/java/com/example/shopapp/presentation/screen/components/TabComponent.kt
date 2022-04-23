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
fun CharacteristicList(product: ProductDto) {
    Box(
        modifier = Modifier.padding(10.dp).fillMaxSize()
    ) {
        LazyColumn(modifier = Modifier.padding(5.dp).fillMaxSize()) {
            item {
                Characteristic("Бранд:", product.brand)
                Characteristic("Тип корпуса:", product.shell_type)
                Characteristic("Верхняя дека:", product.top_deck)
                Characteristic("Материал топа:", product.top_material)
                Characteristic("Задняя дека:", product.back_deck)
                Characteristic("Материал грифа:", product.neck_material)
                Characteristic("Накладка:", product.overlay)
                Characteristic("Количество струн:", product.strings)
                Characteristic("Крепление грифа:", product.neck_attachment)
                Characteristic("Мензура:", product.mensura)
                Characteristic("Ширина грифа:", product.neck_width)
                Characteristic("Цвет:", product.color)
                Characteristic("Струнодержатель:", product.tailpiece)
                Characteristic("Произведено:", product.produced)
                Characteristic("Вырез:", product.cutout)
                Characteristic("Лак:", product.varnish)
                Characteristic("Форма:", product.form)
                Characteristic("Особенности:", product.specials)
                Characteristic("Количество ладов:", product.lads)
            }
        }
    }
}


@Composable
fun Characteristic(name: String, value: String?) {
    if (value != null)
        Row {
            Text(text = name,
                modifier = Modifier
                    .fillMaxWidth(0.5f))
            Spacer(modifier = Modifier
                .fillMaxWidth(0.3f))
            Text(text = value)
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
            1 -> CharacteristicList(product)
        }
    }
}