package com.example.samnotes.presentation.add_edit_note

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.add_edit_note.camera.CameraViewModel
import java.io.File
import java.util.*
import java.util.concurrent.Executor
var imageCapture: ImageCapture? = null

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CameraView(
    outputDirectory: File,
    executor: Executor,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel = hiltViewModel()
) {
    // 1
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var showCamera = viewModel.showCamera.value
    var showPhoto = viewModel.showPhoto.value
    val photoUri = viewModel.photoUri

    val previewView = remember { PreviewView(context) }
    if (showCamera) {
        LaunchedEffect(lensFacing) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener(
                {
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build()
                    imageCapture = ImageCapture.Builder().build()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    val cameraSelector = CameraSelector.Builder()
                        .requireLensFacing(lensFacing)
                        .build()
                    try {
                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageCapture
                        )
                    } catch (exc: Exception) {
                        Log.e("samcj", "Use case binding failed", exc)
                    }
                },
                ContextCompat.getMainExecutor(context)
            )
        }

        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {

            AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

            IconButton(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = {
                    viewModel.takePhoto(
                        outputDirectory = outputDirectory,
                        imageCapture = imageCapture,
                        executor = executor,
                        onError = onError,
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Sharp.Lens,
                        contentDescription = "Take picture",
                        tint = Color.White,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(1.dp)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            )
        }
    }

    if (showPhoto) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            Image(
                painter = rememberImagePainter(data = photoUri),
                contentDescription = "captured image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Row(
                 verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                    ,
                    onClick = {
                        Log.i("samcj", "imageSaved uri: $photoUri")
                        showPhoto = false
                        println(showPhoto)
                        showCamera  = true
                        println(showCamera)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription ="check",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(1.dp)
                                .border(1.dp, Color.Black, CircleShape),
                            tint = Color.Red
                        )
                    }
                )
                IconButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                    ,
                    onClick = {

                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "arrow circle right",
                            modifier = Modifier
                                .size(70.dp)
                                .padding(1.dp)
                                .border(1.dp, Color.Black, CircleShape),
                            tint = Color.White
                        )
                    }
                )
            }
        }
    }
}


