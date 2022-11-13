package com.example.notify.feature_note.domain.repository

import com.example.notify.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

// in the domain there is no implementation for the repository but it done in the data layer

interface NoteRepository {

     fun getAllNotes(): Flow<List<Note>> // make it flow

    suspend fun getNoteById(id: Int): Note?

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)
}