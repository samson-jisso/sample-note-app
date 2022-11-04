package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.repository.NoteRepository

class InsertNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note:NoteEntity): Long{
        return repository.insertNote(note)
    }
}