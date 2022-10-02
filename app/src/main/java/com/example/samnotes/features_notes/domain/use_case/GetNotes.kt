package com.example.samnotes.features_notes.domain.use_case

import com.example.samnotes.features_notes.domain.model.Note
import com.example.samnotes.features_notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}