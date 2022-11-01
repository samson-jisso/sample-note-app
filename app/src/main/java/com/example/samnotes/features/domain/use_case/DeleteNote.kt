package com.example.samnotes.features.domain.use_case

import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.domain.repository.NoteRepository
import java.util.concurrent.Flow

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(noteId:NoteEntity){
        repository.deleteNote(noteId)
    }
}