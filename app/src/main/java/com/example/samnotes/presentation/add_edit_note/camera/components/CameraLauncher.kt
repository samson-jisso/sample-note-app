package com.example.samnotes.presentation.add_edit_note.camera.components

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.samnotes.presentation.add_edit_note.camera.CameraViewModel
import java.io.File
import java.util.concurrent.Executor

var imageCapture: ImageCapture? = null

@Composable
fun CameraLauncher(
    navController: NavController,
    outputDirectory: File,
    executor: Executor,
    onError: (ImageCaptureException) -> Unit,
    viewModel: CameraViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current

    val previewView = remember { PreviewView(context) }
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
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
//    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        IconButton(
            onClick = {
                navController.navigateUp()
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Take picture",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(20.dp)
                        .background(Color.White, shape = CircleShape)
                )
            }
        )
        AndroidView(
            { previewView }, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconButton(

                modifier = Modifier.padding(bottom = 20.dp, top = 5.dp),
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
                        imageVector = Icons.Default.Camera,
                        contentDescription = "Take picture",
                        tint = Color.White,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(5.dp)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            )
        }
    }

//    }
}