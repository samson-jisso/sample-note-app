package com.example.samnotes.presentation.navigation

sealed class Screen(val route: String) {
    object NotesScreen:Screen("note_screen")
    object NotesEditScreen:Screen("note_edit_screen")

}