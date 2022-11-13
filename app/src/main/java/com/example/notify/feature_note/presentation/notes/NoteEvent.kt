package com.example.notify.feature_note.presentation.notes

import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.util.NoteOrder

sealed class NoteEvent{
    data class Order(val order: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSection : NoteEvent()
}
