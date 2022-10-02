package com.example.samnotes.features_notes.domain.use_case

import com.example.samnotes.features_notes.domain.model.Note
import com.example.samnotes.features_notes.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int): Note? {
       return repository.getNoteById(id)
    }
}