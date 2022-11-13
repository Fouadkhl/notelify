package com.example.notify.feature_note.presentation.add_edit_notes

sealed class EditNoteEvent{
    data class EnteredTitleEvent(val title : String) : EditNoteEvent()
    data class EnteredDescriptionEvent(val  desciption : String) : EditNoteEvent()
    data class ChangeColorEvent(val color : Int) : EditNoteEvent()
    object SaveNote : EditNoteEvent()
}
