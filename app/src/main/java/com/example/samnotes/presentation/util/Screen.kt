package com.example.samnotes.presentation.util

sealed class Screen( val route:String) {
    object NotesScreen:Screen("notes_screen")
    object NoteEditScreen: Screen("note_edit_screen")
    object CameraView:Screen("camera_view")
}
