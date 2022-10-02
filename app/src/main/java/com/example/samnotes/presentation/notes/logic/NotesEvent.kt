package com.example.samnotes.presentation.notes.logic

import com.example.samnotes.features_notes.domain.model.Note

sealed class NotesEvent {
    object GetNotes : NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
}
