package com.example.samnotes.features.data.local.repositoy

import androidx.lifecycle.LiveData
import com.example.samnotes.features.data.local.db.NoteDao
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImp(
    val noteDao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> = noteDao.getNotes()

    override suspend fun insertNote(note: NoteEntity):Long = noteDao.insertNote(note)


    override suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)

    override fun getSingleNote(noteId: Int): Flow<NoteEntity> = noteDao.getSingleNote(noteId)

    override suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)

    override suspend fun getIdFromRowId(rowId: Long): Int = noteDao.getIdFromRow(rowId)
}