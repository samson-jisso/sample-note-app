package com.example.samnotes.features_notes.domain.use_case

import com.example.samnotes.features_notes.domain.model.InvalidNoteException
import com.example.samnotes.features_notes.domain.model.Note
import com.example.samnotes.features_notes.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note:Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("Title can't be empty")
        }
        if(note.content.isBlank()) {
            throw  InvalidNoteException("content of the note can't be null")
        }
        repository.insertNote(note)
    }
}