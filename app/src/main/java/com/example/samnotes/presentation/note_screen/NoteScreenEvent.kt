package com.example.samnotes.presentation.note_screen

import android.net.Uri


sealed class NoteScreenEvent {
    data class SelectNote(val id: Int? = null) : NoteScreenEvent()
    data class SelectOrDeselectNote(val id: Int? = null) : NoteScreenEvent()
    data class LongPressClicked(val clickable: Boolean = true) : NoteScreenEvent()
    object BackPressed : NoteScreenEvent()
    object DeleteSelectedNotes : NoteScreenEvent()
}