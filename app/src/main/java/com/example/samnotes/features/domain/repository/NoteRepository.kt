package com.example.samnotes.features.domain.repository

import com.example.samnotes.features.domain.model.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    suspend fun getNotes():Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note:Note)
    suspend fun getSingleNote(noteId: Int):Note?
    suspend fun updateNote(note: Note)
    suspend fun getIdFromRowId(rowId: Long):Int
    suspend fun deleteSingleNote(noteId: Int)
}