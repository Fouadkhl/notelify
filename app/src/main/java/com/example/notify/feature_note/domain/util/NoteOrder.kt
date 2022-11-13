package com.example.notify.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class ColorPriorety(orderType: OrderType) : NoteOrder(orderType)
}
