package com.example.notify.feature_note.domain.use_cases

data class NoteUseCases(
    val getNote: GetNote ,
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)

