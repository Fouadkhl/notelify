package com.example.notify.feature_note.domain.use_cases

import com.example.notify.feature_note.domain.model.Note
import com.example.notify.feature_note.domain.repository.NoteRepository
import com.example.notify.feature_note.domain.util.NoteOrder
import com.example.notify.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    val repository: NoteRepository
) {
    operator fun invoke(
        order: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ) : Flow<List<Note>> {
        return repository.getAllNotes().map { notes ->
            when(order.orderType) {
                is OrderType.Descending -> {
                    when(order) {
                        is NoteOrder.Date ->   notes.sortedBy { it.timestamp }
                        is NoteOrder.Title ->   notes.sortedBy { it.title }
                        is NoteOrder.ColorPriorety -> notes.sortedBy { it.colorPriority }
                    }
                }
                is OrderType.Ascending -> {
                    when(order) {
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Title ->notes.sortedByDescending { it.title }
                        is NoteOrder.ColorPriorety ->notes.sortedByDescending { it.colorPriority }
                    }
                }
            }
        }
    }
}