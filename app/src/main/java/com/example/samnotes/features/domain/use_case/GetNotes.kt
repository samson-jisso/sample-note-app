package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository:NoteRepository
) {
    operator fun invoke(): Flow<List<NoteEntity>> {
        return repository.getNotes()
    }
}