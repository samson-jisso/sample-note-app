package com.example.samnotes.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.samnotes.R
import com.example.samnotes.presentation.add_edit_note.camera.CameraView
import com.example.samnotes.presentation.add_edit_note.NoteEditScreen
import com.example.samnotes.presentation.notes.NoteScreen
import com.example.samnotes.presentation.notes.logic.NotesViewModel
import com.example.samnotes.presentation.util.Screen
import com.example.samnotes.ui.theme.SamNotesTheme
import com.google.accompanist.permissions.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private val viewModel: NotesViewModel by viewModels()
    private val permissions = listOf(
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_FINE_LOCATION,
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

                            route = Screen.NoteEditScreen.route + "?noteId={noteId}&photoUri={photoUri}" ,
                            arguments = listOf(
                                navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                                navArgument(
                                    name = "photoUri"
                                ){
                                    type = NavType.StringType
                                    nullable = true
                                }
                            )
                        ) {
                            val arg = it.arguments?.getInt("noteId")
                            val photoUri = it.arguments?.getString("photoUri")
                            NoteEditScreen(
                                photoUri = photoUri,
                                noteIdValue = arg,
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.CameraView.route + "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val arg = it.arguments?.getInt("noteId")
                            CameraView(
                                noteIdValue = arg,
                                navController = navController,
                                outputDirectory = outputDirectory,
                                executor = cameraExecutor,
                                onError = { Log.e("sam", "ViewError", it) }
                            )
                        }
                    }
                }
            }
        }
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}