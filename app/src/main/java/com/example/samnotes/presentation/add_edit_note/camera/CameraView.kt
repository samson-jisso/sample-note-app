package com.example.samnotes.presentation.add_edit_note

import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.samnotes.presentation.add_edit_note.camera.CameraViewModel
import com.example.samnotes.presentation.add_edit_note.camera.components.CameraLauncher
import com.example.samnotes.presentation.add_edit_note.camera.components.PicturePreview
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditViewModel
import java.io.File
import java.util.concurrent.Executor


@Composable
fun CameraView(
    outputDirectory: File,
    executor: Executor,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController
) {
    // 1
    if (viewModel.showCamera.value) {
        CameraLauncher(
            outputDirectory,
            executor,
            onError,
            viewModel
        )
    }

    if (viewModel.showPhoto.value) {
        PicturePreview(
            viewModel = viewModel,
            navController = navController
        )
    }
}



