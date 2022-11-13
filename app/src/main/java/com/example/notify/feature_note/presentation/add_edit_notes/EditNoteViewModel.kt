package com.example.notify.feature_note.presentation.add_edit_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notify.feature_note.domain.model.InvalidNoteException
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle // contain saved argument passed during the navigation --like bundle--
) : ViewModel() {

    /****************** States  *************************/

    // we will not wrap all states  in note state because it will rerender all the screen
    private val _titleState = mutableStateOf("")
    val titleState: State<String> = _titleState

    private val _descriptionState = mutableStateOf("")
    val descriptionState: State<String> = _descriptionState

    private val _colorState = mutableStateOf(Note.noteColors.random().toArgb())
    val colorState: State<Int> = _colorState

    private var currentnoteId: Int? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    init {
        savedStateHandle.get<Int>("noteId")?.let { noteid ->
            if (noteid != -1) {
                viewModelScope.launch {
                    currentnoteId = noteid
                    noteUseCases.getNote(currentnoteId!!)?.also { note ->
                        currentnoteId = note.id
                        _titleState.value = note.title
                        _descriptionState.value = note.description
                        _colorState.value = note.colorPriority
                    }
                }
            }
        }
    }

    /****************** Events  *************************/

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.EnteredTitleEvent -> {
                _titleState.value = event.title
            }

            is EditNoteEvent.EnteredDescriptionEvent -> {
                _descriptionState.value = event.desciption
            }

            is EditNoteEvent.ChangeColorEvent -> {
                _colorState.value = event.color
            }

            is EditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = _titleState.value,
                                description = _descriptionState.value,
                                timestamp = System.currentTimeMillis(),
                                id = currentnoteId,
                                colorPriority = _colorState.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)

                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("couldn't save the note"))
                    }
                }
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}