package com.example.notify.feature_note.data.repository

import com.example.notify.feature_note.data.data_source.NoteDao
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    val noteDao: NoteDao
) : NoteRepository {
    override  fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}