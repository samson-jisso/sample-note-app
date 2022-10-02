package com.example.samnotes.presentation.notes.logic

import com.example.samnotes.features_notes.domain.model.Note

data class NoteState(
    val notes: List<Note> = emptyList()
)
