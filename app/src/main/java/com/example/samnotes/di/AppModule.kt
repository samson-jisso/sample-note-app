package com.example.samnotes.di

import android.app.Application
import androidx.room.Room
import com.example.samnotes.features_notes.data.data_source.NoteDatabase
import com.example.samnotes.features_notes.data.repository.NoteRepositoryImpl
import com.example.samnotes.features_notes.domain.repository.NoteRepository
import com.example.samnotes.features_notes.domain.use_case.*
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
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        db: NoteDatabase
    ): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            addNote = AddNote(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            getNoteById = GetNoteById(noteRepository),
            getNotes = GetNotes(noteRepository)
        )
    }
}