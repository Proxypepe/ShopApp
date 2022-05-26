package com.example.shopapp



fun <T> divideList(list: List<T>): Pair<MutableList<T?>, MutableList<T?>> {
    val maxSize = list.size
    val middle = maxSize / 2
    val first: MutableList<T?> = list.subList(0, middle).toMutableList()
    val second: MutableList<T?> = list.subList(middle, maxSize).toMutableList()
    if (first.size > second.size)
        second.add(null)
    else if (first.size < second.size)
        first.add(null)

    return Pair(
        first,
        second
    )
}