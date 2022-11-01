package com.example.samnotes.features.data.local.repositoy

import com.example.samnotes.features.data.local.db.NoteDao
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImp(
    val noteDao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
      return noteDao.getNotes()
    }

    override suspend fun insertNote(note: NoteEntity) {
       return noteDao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
      return noteDao.deleteNote(note)
    }

    override suspend fun getSingleNote(noteId: Int):List<NoteEntity> {
        return noteDao.getSingleNote(noteId)
    }
}