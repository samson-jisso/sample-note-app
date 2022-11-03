package com.example.samnotes.presentation.note_screen

import java.net.IDN

sealed class NoteState {
    data class NoteData(
        val title: String,
        val content: String
    ) : NoteState()
}
