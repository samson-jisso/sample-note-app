package com.example.samnotes.di

import android.app.Application
import androidx.room.Room
import com.example.samnotes.features.data.local.db.NoteDatabase
import com.example.samnotes.features.data.local.repositoy.NoteRepositoryImp
import com.example.samnotes.features.domain.repository.NoteRepository
import com.example.samnotes.features.domain.use_case.*
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
            NoteDatabase.databaseName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase):NoteRepository {
        return NoteRepositoryImp(
            noteDao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository:NoteRepository):NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            getSingleNote = GetSingleNote(repository),
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            getIDFromInserted = GetIDFromInserted(repository)
        )
    }


}