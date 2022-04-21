package com.example.shopapp.domain.filters

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Category (
    val category: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
) {
    object Acoustic: Category("Акустические гитары")
    object Classic: Category("Классическая гитара")
    object Electro: Category("Электрогитары")
    object Bass: Category("Басс-гитара")
    object Unknown: Category("Unknown")
}

sealed class Brand(
    val brand: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
) {
    object Prima: Brand("PRIMA")
    object Yamaha: Brand("Yamaha")
    object Crafter: Brand("Crafter")
    object EnyaGuitars: Brand("Enya Guitars")
    object Framus: Brand("Framus")
    object Ovation: Brand("Ovation")
    object Cort: Brand("Cort")
    object Fender: Brand("Fender")
    object LAG: Brand("LAG")
    object Ortega: Brand("Ortega")
    object Kremona: Brand("Kremona")
    object GEWA: Brand("Pro Arte by GEWA")
    object Django: Brand("Django")
    object Alhambra: Brand("Alhambra")
    object Tokai: Brand("Tokai")
    object Vintage: Brand("Vintage")
    object Inspector: Brand("Inspector")
    object DBZ: Brand("DBZ")
    object Squier: Brand("Squier")
    object Ibanez: Brand("Ibanez")
    object FGN: Brand("FGN")
    object PRS: Brand("PRS")
    object Mayones: Brand("Mayones")
    object Angelico: Brand("D'Angelico")
    object Fernandes: Brand("Fernandes")
    object Unknown: Brand("Unknown")
}

sealed class Manufacturer(
    val country: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
) {
    object China: Manufacturer("Китай")
    object Indonesia: Manufacturer("Индонезия")
    object Bulgaria: Manufacturer("Болгария")
    object Romania: Manufacturer("Румыния")
    object Spain: Manufacturer("Испания")
    object Russia: Manufacturer("Россия")
    object Korea: Manufacturer("Корея")
    object Japan: Manufacturer("Япония")
    object Mexico: Manufacturer("Мексика")
    object Poland: Manufacturer("Польша")
    object USA: Manufacturer("США")
    object Unknown: Manufacturer("Unknown")
}

sealed class Color(
    val color: String,
    var selected: MutableState<Boolean> = mutableStateOf(false)
) {
    object Natural: Color("Натуральный")
    object Black: Color("Чёрный")
    object Burst: Color("Burst")
    object Brown: Color("Коричневый")
    object White: Color("Белый")
    object Silver: Color("Серебряный")
    object Red: Color("Красный")
    object BlackE: Color("Черный")
    object Grey: Color("Серый")
    object Blue: Color("Голубой")
    object DarkBlue: Color("Синий")
    object Orange: Color("Оранжевый")
    object Pink: Color("Розовый")
    object Nacre: Color("Перламутр")
    object Yellow: Color("Желтый")
    object Green: Color("Зелёный")
}


fun getCategoryFromStringValue(uiValue: String): Category {
    return when(uiValue) {
        Category.Acoustic.category -> Category.Acoustic
        Category.Classic.category -> Category.Classic
        Category.Electro.category -> Category.Electro
        Category.Bass.category -> Category.Bass

        else -> Category.Unknown
    }
}


fun getBrandFromStringValue(uiValue: String): Brand {
    return when(uiValue) {
        Brand.Prima.brand -> Brand.Prima
        Brand.Yamaha.brand -> Brand.Yamaha
        Brand.Crafter.brand -> Brand.Crafter
        Brand.EnyaGuitars.brand -> Brand.EnyaGuitars
        Brand.Framus.brand -> Brand.Framus
        Brand.Ovation.brand -> Brand.Ovation
        Brand.Cort.brand -> Brand.Cort
        Brand.Fender.brand -> Brand.Fender
        Brand.LAG.brand -> Brand.LAG
        Brand.Ortega.brand -> Brand.Ortega
        Brand.Kremona.brand -> Brand.Kremona
        Brand.GEWA.brand -> Brand.GEWA
        Brand.Django.brand -> Brand.Django
        Brand.Alhambra.brand -> Brand.Alhambra
        Brand.Tokai.brand -> Brand.Tokai
        Brand.Vintage.brand -> Brand.Vintage
        Brand.Inspector.brand -> Brand.Inspector
        Brand.DBZ.brand -> Brand.DBZ
        Brand.Squier.brand -> Brand.Squier
        Brand.Ibanez.brand -> Brand.Ibanez
        Brand.FGN.brand -> Brand.FGN
        Brand.PRS.brand -> Brand.PRS
        Brand.Mayones.brand -> Brand.Mayones
        Brand.Angelico.brand -> Brand.Angelico
        Brand.Fernandes.brand -> Brand.Fernandes

        else -> Brand.Unknown
    }
}