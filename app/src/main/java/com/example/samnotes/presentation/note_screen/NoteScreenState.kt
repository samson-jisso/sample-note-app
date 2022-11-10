package com.example.samnotes.presentation.note_screen

import com.example.samnotes.features.data.local.entity.NoteEntity

data class NoteScreenState(
    var data:List<NoteEntity> = emptyList()
)
