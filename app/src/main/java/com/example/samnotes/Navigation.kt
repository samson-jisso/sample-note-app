package com.example.samnotes

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.samnotes.presentation.add_edit_note.CameraView
import com.example.samnotes.presentation.add_edit_note.NoteEditScreen
import com.example.samnotes.presentation.notes.NoteScreen
import com.example.samnotes.presentation.notes.logic.NotesViewModel
import com.example.samnotes.presentation.util.Screen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import java.io.File
import java.util.concurrent.ExecutorService

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Navigation(
    outputDirectory:File,
    navController: NavHostController,
    cameraExecutor: ExecutorService,
    multiplePermissionsState:MultiplePermissionsState,
    viewModel:NotesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route,
    ) {

        composable(route = Screen.NotesScreen.route) {
            NoteScreen(
                navController = navController,
                onRequestPermission = {
                    multiplePermissionsState.launchMultiplePermissionRequest()
                    viewModel.ClosePermissionDialog()
                },
                viewModel
            )
        }
        composable(
            route = Screen.NoteEditScreen.route + "?noteId={noteId}&photoUri={photoUri}",
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
                    defaultValue = null
                    nullable = true
                }
            )
        ) { arg ->
            NoteEditScreen(
                arg = arg.arguments?.getString("photoUri"),
                navController = navController,
            )
        }
        composable(
            route = Screen.CameraView.route,
        ) {
            CameraView(
                navController = navController,
                outputDirectory = outputDirectory,
                executor = cameraExecutor,
                onError = { Log.e("sam", "ViewError", it) }
            )
        }
    }
}