package com.example.samnotes.presentation.add_edit_note.camera

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.samnotes.presentation.MainActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

class CameraViewModel:ViewModel() {
    var showPhoto: MutableState<Boolean> = mutableStateOf(false)
    var showCamera: MutableState<Boolean> = mutableStateOf(true)
    var photoUri: Uri? = null
    @RequiresApi(Build.VERSION_CODES.S)
     fun takePhoto(
        outputDirectory: File,
        imageCapture: ImageCapture?,
        executor: Executor,
        onError: (ImageCaptureException) -> Unit
    ) {
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                MainActivity.FILENAME_FORMAT,
                Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture!!.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                Log.e("samcj", "Take photo error:", exception)
                onError(exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                handleImageCapture(savedUri)
            }
        })
    }

    private fun handleImageCapture(uri: Uri) {
        photoUri = uri
        showCamera.value = false
        showPhoto.value = true
    }

}