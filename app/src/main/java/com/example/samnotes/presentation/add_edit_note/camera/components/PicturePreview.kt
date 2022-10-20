package com.example.samnotes.presentation.add_edit_note.camera.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.add_edit_note.camera.CameraViewModel
import com.example.samnotes.presentation.util.Screen
import java.io.File

@Composable
fun PicturePreview(
    noteId:Int?,
    navController: NavController,
    cameraViewModel: CameraViewModel
) {
    val photoUri = cameraViewModel.photoUri
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
                    .weight(1f),
                onClick = {
                    Log.i("samcj", "imageSaved uri: $photoUri")
                    val file = File(photoUri!!.path.toString())
                    if (file.exists()) {
                        file.delete()
                    }
                    cameraViewModel.showPhoto.value = false
                    cameraViewModel.showCamera.value = true
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "check",
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
                    .weight(1f),
                onClick = {
                    navController.navigate(
                        Screen.NoteEditScreen.route +
                                "?noteId=${noteId}&photoUri=${photoUri}"
                    )
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