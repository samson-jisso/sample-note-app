package com.example.samnotes.presentation.camera_view.presentation

import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.samnotes.presentation.camera_view.logic.CameraViewModel
import com.example.samnotes.presentation.camera_view.presentation.component.CameraLauncher
import com.example.samnotes.presentation.camera_view.presentation.component.PicturePreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraView(
    onNavCameraToNoteEditScreen:(noteId:Int, photoUri:String) -> Unit,
    outPutDirectory: File,
    executor:Executor,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel = hiltViewModel(),
) {
    if (viewModel.showCamera.value) {
        CameraLauncher(
            outPutDirectory,
            executor,
            onError,
            viewModel
        )
    }

    if (viewModel.showPhoto.value) {
        PicturePreview(
            onNavCameraToNoteEditScreen,
            viewModel = viewModel
        )
    }

}