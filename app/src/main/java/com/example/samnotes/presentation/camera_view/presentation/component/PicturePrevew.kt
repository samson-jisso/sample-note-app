package com.example.samnotes.presentation.camera_view.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureEntity
import com.example.samnotes.presentation.camera_view.logic.CameraViewModel
import com.example.samnotes.presentation.navigation.Screen

@Composable
fun PicturePreview(
    onNavCameraToNoteEditScreen: (noteId: Int, photoUri: String) -> Unit,
    viewModel: CameraViewModel,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val (image, row) = createRefs()
        Image(
            painter = rememberImagePainter(data = viewModel.photoUri.value),
            contentDescription = "captured image",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(row.top, margin = 4.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                },
            contentScale = ContentScale.FillHeight
        )
        Row(
            modifier = Modifier
                .constrainAs(row) {
                    bottom.linkTo(parent.bottom /*margin = 4.dp*/)
                    width = Dimension.wrapContent
                }
        ) {
            //clear button
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                onClick = {
                    viewModel.showPhotoState()
                    viewModel.showCameraState()
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "check",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(1.dp)
                            .border(1.dp, MaterialTheme.colors.onBackground, CircleShape),
                    )
                }
            )
            //check button
            IconButton(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                onClick = {
                    viewModel.noteId?.let { noteId ->
                        onNavCameraToNoteEditScreen(noteId, viewModel.photoUri.value.toString())
                    }
                    viewModel.insertNotePicture(
                        viewModel.noteId?.let { noteId ->
                            PictureEntity(
                                noteId = noteId,
                                pictureAddress = viewModel.photoUri.value.toString(),
                                pictureId = viewModel.randomIdNum()
                            )
                        }
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "arrow circle right",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(1.dp)
                            .border(1.dp, color = MaterialTheme.colors.onBackground, CircleShape),
                    )
                }
            )
        }
    }
}