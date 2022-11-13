package com.example.notify.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.notify.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes() : Flow<List<Note>> // make it flow

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun  getNoteById(id: Int) : Note?

    @Insert(onConflict = REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}