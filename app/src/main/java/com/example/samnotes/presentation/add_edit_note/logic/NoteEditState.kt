package com.example.samnotes.presentation.add_edit_note.logic

data class NoteEditState(
    val text:String = "",
    val hint:String = "",
    val isHintVisible: Boolean = true
)
