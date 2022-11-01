package com.example.samnotes.features.domain.repository

import com.example.samnotes.features.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getNotes():Flow<List<NoteEntity>>
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(note:NoteEntity)
    suspend fun getSingleNote(noteId: Int):List<NoteEntity>
}