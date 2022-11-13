package com.example.notify.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.use_cases.NoteUseCases
import com.example.notify.feature_note.domain.util.NoteOrder
import com.example.notify.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val noteUseCases: NoteUseCases) : ViewModel() {


    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state


    private var lastDeletedNote: Note? = null

    private var noteJob: Job? = null


    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.ToggleOrderSection -> {
                _state.value =
                    state.value.copy(orderSectionVisibility = !state.value.orderSectionVisibility)
            }
            is NoteEvent.Order -> {
                // if it is the same order as privious dont do anything
                if (state.value.noteOrder::class == event.order::class
                    && state.value.noteOrder.orderType == event.order.orderType
                ) {
                    return
                }
                getNotesWithOrder(event.order)
            }
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    lastDeletedNote = event.note
                }
            }
            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }
        }
    }

    private fun getNotesWithOrder(order: NoteOrder) {
        noteJob?.cancel()
        noteJob = noteUseCases.getNotes(order)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = order
                )
            }.launchIn(viewModelScope)
    }

    init {
        getNotesWithOrder(NoteOrder.Date(OrderType.Descending))
    }

}