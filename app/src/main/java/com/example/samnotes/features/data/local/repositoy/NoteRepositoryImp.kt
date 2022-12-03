package com.example.samnotes.features.data.local.repositoy

import androidx.lifecycle.LiveData
import com.example.samnotes.features.data.local.db.NoteDao
import com.example.samnotes.features.domain.model.Note
import com.example.samnotes.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImp(
    val noteDao: NoteDao
) : NoteRepository {
    override suspend fun getNotes(): Flow<List<Note>> = flow {
        emit(noteDao.getNotes().map { it.toNoteDomain() })
    }


    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toNoteEntity())
    }


    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note.toNoteEntity())

    override suspend fun getSingleNote(noteId: Int): Note {
        val noteData = noteDao.getSingleNote(noteId)
        return noteData.toNoteDomain()
    }

    override suspend fun updateNote(note: Note) = noteDao.updateNote(note.toNoteEntity())

    override suspend fun getIdFromRowId(rowId: Long): Int = noteDao.getIdFromRow(rowId)
    override suspend fun deleteSingleNote(noteId: Int) {
        noteDao.deleteNoteId(noteId)
    }
}