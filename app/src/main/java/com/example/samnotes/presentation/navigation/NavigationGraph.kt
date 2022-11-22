package com.example.samnotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samnotes.presentation.note_edit_screen.NoteEditScreen
import com.example.samnotes.presentation.note_screen.NotesScreen

@Composable
fun NavigationGraph() {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.NotesScreen.route) {
        composable(
            route = Screen.NotesScreen.route
        ) {
            NotesScreen(navHostController)
        }
        composable(
            route = Screen.NotesEditScreen.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            NoteEditScreen(navHostController)
        }
    }
}