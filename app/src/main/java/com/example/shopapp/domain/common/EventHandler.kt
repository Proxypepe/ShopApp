package com.example.shopapp.domain.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
