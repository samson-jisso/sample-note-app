package com.example.samnotes.presentation.camera_view.logic

import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class CameraViewModel
@Inject
constructor(
    val useCases: CameraUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _showCamera = mutableStateOf(true)
    val showCamera: State<Boolean> = _showCamera

    private var _showPhoto = mutableStateOf(false)
    val showPhoto: State<Boolean> = _showPhoto

    private var _photoUri: MutableState<Uri?> = mutableStateOf(null)
    val photoUri:State<Uri?> = _photoUri

    val noteId:Int? = savedStateHandle.get<Int>("noteId")



    fun takePhoto(
        outputDirectory: File,
        imageCapture: ImageCapture?,
        executor: Executor,
        onError: (ImageCaptureException) -> Unit
    ) {
        val photoFileName = File(
            outputDirectory,
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS",
                Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFileName).build()

        imageCapture!!.takePicture(
            outputOptions,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e("samcj", "Take photo error:", exception)
                    onError(exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFileName)
                    handleImageCapture(savedUri)
                }
            })
    }

    fun showCameraState() {
        _showCamera.value = !showCamera.value
    }

    fun showPhotoState() {
        _showPhoto.value = !showPhoto.value
    }

    private fun handleImageCapture(uri: Uri) {
        _photoUri.value = uri
        _showCamera.value = false
        _showPhoto.value = true
    }
}