package com.example.samnotes.features.data.local.entity

import com.example.samnotes.presentation.note_screen.NoteState

sealed class NoteEvent{
    data class UpdateNoteTitle(val titleEntered:String? = null):NoteEvent()
    data class UpdateNoteContent(val contentEntered:String? = null ):NoteEvent()
}
