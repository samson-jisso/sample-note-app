package com.example.samnotes.presentation.add_edit_note.camera

import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import com.example.samnotes.presentation.add_edit_note.camera.components.CameraLauncherView
import com.example.samnotes.presentation.add_edit_note.camera.components.PicturePreview
import java.io.File
import java.util.concurrent.Executor

@Composable
fun CameraView(
    noteIdValue:Int?,
    navController: NavController,
    outputDirectory: File,
    executor: Executor,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {
    val showCamera = viewModel.showCamera
    val showPhoto = viewModel.showPhoto

    if (showCamera.value) {
        CameraLauncherView(
            outputDirectory = outputDirectory,
            executor = executor,
            onError = onError,
            cameraViewModel = viewModel
        )
    }

    if (showPhoto.value) {
        PicturePreview(
            noteId = noteIdValue,
            navController = navController,
            cameraViewModel = viewModel
        )
    }
}


