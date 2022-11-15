package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.domain.model.Note
import com.example.samnotes.features.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository:NoteRepository
) {
   suspend operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}