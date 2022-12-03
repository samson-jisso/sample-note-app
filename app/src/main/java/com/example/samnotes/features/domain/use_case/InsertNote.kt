package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.domain.model.Note
import com.example.samnotes.features.domain.repository.NoteRepository

class InsertNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.insertNote(note)
    }
}