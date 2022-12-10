package com.example.samnotes.presentation.camera_view.logic

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureEntity
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.CameraUseCases
import com.example.samnotes.presentation.camera_view.presentation.CameraEvent
import com.example.samnotes.presentation.randomIdNum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class CameraViewModel
@Inject
constructor(
    private val useCases: CameraUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _showCamera = mutableStateOf(true)
    val showCamera: State<Boolean> = _showCamera

    private var _showPhoto = mutableStateOf(false)
    val showPhoto: State<Boolean> = _showPhoto

    private var _photoUri: MutableState<Uri?> = mutableStateOf(null)
    val photoUri: State<Uri?> = _photoUri

    val noteId: Int? = savedStateHandle.get<Int>("noteId")
    var randomPictureId: Int? = null

    fun handleEvent(event: CameraEvent) {
        when (event) {
            is CameraEvent.OnSave -> {
                randomPictureId = randomIdNum()
                viewModelScope.launch {
                    useCases.insertPicture(
                        noteId?.let { noteId ->
                            PictureEntity(
                                noteOwnerId = noteId,
                                pictureAddress = photoUri.value.toString(),
                                pictureId = randomPictureId!!
                            )
                        }
                    )
                }
            }
            is CameraEvent.OnUpdate -> {

                viewModelScope.launch(Dispatchers.IO) {
                    randomPictureId = useCases.getPictureId(
                        event.pictureAddress
                    )

                    useCases.updateNotePicture(
                        noteId?.let { noteId ->
                            PictureEntity(
                                pictureId =randomPictureId!!,
                                pictureAddress = event.pictureAddress,
                                noteOwnerId = noteId,
                                offsetX = event.offsetX.toString(),
                                offsetY = event.offsetY.toString()
                            )
                        }

                    )
                }
            }

        }
    }

    fun getContentValues(photoName: String): ContentValues {
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, photoName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ComposeImages")
            }
        }
    }


    fun takePhoto(
        outputDirectory: ImageCapture.OutputFileOptions,
        imageCapture: ImageCapture?,
        executor: Executor,
        onError: (ImageCaptureException) -> Unit
    ) {

        imageCapture!!.takePicture(
            outputDirectory,
            executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e("samcj", "Take photo error:", exception)
                    onError(exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
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

    private fun handleImageCapture(uri: Uri?) {
        _photoUri.value = uri
        _showCamera.value = false
        _showPhoto.value = true
    }
}