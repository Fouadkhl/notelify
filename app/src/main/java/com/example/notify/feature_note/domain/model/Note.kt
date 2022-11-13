package com.example.notify.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notify.ui.theme.*


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val timestamp: Long,
    val colorPriority: Int = noteColors[0].toArgb()
) {
    companion object {
        val noteColors = listOf(
            RedOrange,
            RedPink,
            BabyBlue,
            Violet,
            LightGreen
        )
    }
}

class  InvalidNoteException (message : String) : Exception(message)
