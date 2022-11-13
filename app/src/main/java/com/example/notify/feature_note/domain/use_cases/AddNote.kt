package com.example.notify.feature_note.domain.use_cases

import com.example.notify.feature_note.domain.model.InvalidNoteException
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

    class AddNote @Inject constructor(val noteRepository: NoteRepository) {


    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()){
            throw InvalidNoteException("the note title should not be empty")
        }
        if (note.description.isBlank()){
            throw InvalidNoteException("the note description should not be empty")
        }
        noteRepository.addNote(note)
    }
}