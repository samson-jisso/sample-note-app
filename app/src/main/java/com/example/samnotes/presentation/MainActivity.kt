package com.example.samnotes.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samnotes.presentation.add_edit_note.NoteEditScreen
import com.example.samnotes.presentation.notes.NoteScreen
import com.example.samnotes.presentation.notes.logic.NotesViewModel
import com.example.samnotes.presentation.util.Screen
import com.example.samnotes.ui.theme.SamNotesTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    private val viewModel: NotesViewModel by viewModels()
    private val permissions = listOf(
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val multiplePermissionsState =
                rememberMultiplePermissionsState(permissions = permissions)
            val result = multiplePermissionsState.permissions.all { permissionState ->
                    permissionState.status == PermissionStatus.Granted
            }
            if (result) {
                viewModel.onOpenDialogClicked()
                viewModel.ClosePermissionDialog()
            }
            SamNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.primary
                ) {
                    val navController = rememberNavController()
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
                            route = Screen.NoteEditScreen.route + "?noteId={noteId}",
                            arguments = listOf(navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            })
                        ) {
                            NoteEditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SamNotesTheme {

    }
}