package com.example.notify.feature_note.domain.use_cases

import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.repository.NoteRepository
import com.example.notify.feature_note.domain.util.NoteOrder
import com.example.notify.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DeleteNote(
    val repository: NoteRepository
) {
    operator suspend fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}