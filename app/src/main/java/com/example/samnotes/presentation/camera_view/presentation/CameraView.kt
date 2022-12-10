package com.example.samnotes.presentation.camera_view.presentation

import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.samnotes.presentation.camera_view.logic.CameraViewModel
import com.example.samnotes.presentation.camera_view.presentation.component.CameraLauncher
import com.example.samnotes.presentation.camera_view.presentation.component.PicturePreview

@Composable
fun CameraView(
    onNavCameraToNoteEditScreen:(noteId:Int) -> Unit,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel = hiltViewModel(),
) {
    if (viewModel.showCamera.value) {
        CameraLauncher(
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