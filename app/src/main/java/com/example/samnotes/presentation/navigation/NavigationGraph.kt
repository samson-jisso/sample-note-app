package com.example.samnotes.presentation.navigation

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samnotes.presentation.camera_view.presentation.CameraView
import com.example.samnotes.presentation.note_edit_screen.NoteEditScreen
import com.example.samnotes.presentation.note_screen.NotesScreen
import java.io.File
import java.util.concurrent.Executor

@Composable
fun NavigationGraph(
    outPutDirectory: File,
    executor: Executor,
) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.NotesScreen.route) {
        composable(
            route = Screen.NotesScreen.route
        ) {
            NotesScreen(navHostController)
        }
        composable(
            route = Screen.NotesEditScreen.route + "?noteId={noteId}&photoUri={photoUri}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "photoUri"
                ) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {
            NoteEditScreen(navHostController)
        }
        composable(
            route = Screen.CameraView.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ){
                    type = NavType.IntType
                    defaultValue  = -1
                }
            )
        ) {
            CameraView(navHostController = navHostController,
                outPutDirectory = outPutDirectory,
                executor = executor,
                onError = { imageCaptureException ->
                    Log.e("sam", "CameraViewError", imageCaptureException)
                })
        }

    }
}