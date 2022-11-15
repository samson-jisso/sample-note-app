package com.example.samnotes.presentation.note_screen


sealed class NoteScreenEvent {
    data class UpdateSelectedData(val index:Int):NoteScreenEvent()

}