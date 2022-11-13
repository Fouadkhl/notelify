package com.example.notify.di

import android.app.Application
import androidx.room.Room
import com.example.notify.feature_note.data.data_source.NoteDatabase
import com.example.notify.feature_note.data.repository.NoteRepositoryImpl
import com.example.notify.feature_note.domain.repository.NoteRepository
import com.example.notify.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application) : NoteDatabase {
        return Room.databaseBuilder(
            app ,
            NoteDatabase::class.java ,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton // we pass the db to get the dao because we dont create a function here to get the dao
    fun provideNoteRepository(db : NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository) : NoteUseCases{
        return NoteUseCases( GetNote(noteRepository), GetNotes(noteRepository) , DeleteNote(noteRepository) , AddNote(noteRepository))
    }

}
