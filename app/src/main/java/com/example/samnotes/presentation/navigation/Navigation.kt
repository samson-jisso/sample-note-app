package com.example.samnotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.samnotes.presentation.note_edit_screen.NoteEditScreen
import com.example.samnotes.presentation.note_screen.NotesScreen

@Composable
fun Navigation() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.NotesScreen.route ) {
        composable(
            route = Screen.NotesScreen.route
        ){
            NotesScreen(navHostController)
        }
        composable(
            route = Screen.NotesEditScreen.route
        ){
            NoteEditScreen()
        }
    }
}