package com.example.samnotes.features.domain.repository

import com.example.samnotes.features.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import java.sql.RowId


interface NoteRepository {
    fun getNotes():Flow<List<NoteEntity>>
    suspend fun insertNote(note: NoteEntity):Long
    suspend fun deleteNote(note:NoteEntity)
    fun getSingleNote(noteId: Int):Flow<NoteEntity>
    suspend fun updateNote(note: NoteEntity)
    suspend fun getIdFromRowId(rowId: Long):Int
    suspend fun deleteSingleNote(noteId: Int)
}