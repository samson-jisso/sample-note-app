package com.example.samnotes.presentation.note_screen


sealed class NoteScreenEvent {
    data class SelectNote(val id: Int) : NoteScreenEvent()
    data class DeselectNote(val id: Int) : NoteScreenEvent()
    data class LongPressClicked(val clickable: Boolean = true) : NoteScreenEvent()
    object BackPressed : NoteScreenEvent()
}