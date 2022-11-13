package com.example.notify.feature_note.domain.util

import androidx.room.FtsOptions

sealed class OrderType{
    object Ascending : OrderType()
    object Descending : OrderType()
}
