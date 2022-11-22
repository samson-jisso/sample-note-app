package com.example.samnotes.presentation.note_screen

import com.example.samnotes.features.domain.model.Note

data class NoteScreenState(
    var data:List<Note> = emptyList(),
//    var selectedNoteId: MutableList<Int> = mutableListOf(),5
    var selectedNoteId: List<Int> = emptyList(),
    var clicked: Boolean = true
)