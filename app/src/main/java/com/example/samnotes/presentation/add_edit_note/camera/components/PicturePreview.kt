package com.example.samnotes.presentation.add_edit_note.camera.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.add_edit_note.camera.CameraViewModel
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditViewModel
import com.example.samnotes.presentation.util.Screen

@Composable
fun PicturePreview(
    viewModel: CameraViewModel,
    navController: NavController,
    noteEditViewModel: NoteEditViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Image(
            painter = rememberImagePainter(data = viewModel.photoUri),
            contentDescription = "captured image",
            modifier = Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth(),
            contentScale = ContentScale.FillHeight
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f, false),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        Log.i("samcj", "imageSaved uri: ${viewModel.photoUri}")
                        viewModel.showPhoto.value = false
                        viewModel.showCamera.value = true
                    },
                color = Color.White,
                text = "retry"
            )
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
//                    navController.navigate(
//                        Screen.NoteEditScreen.route +
//                                "?photoUri=${viewModel.photoUri}"
//                    ){
//                        popUpTo(Screen.CameraView.route) {inclusive = true}
//                    }
                        noteEditViewModel.imageUri(viewModel.photoUri)
                        println("in pictureView model : ${noteEditViewModel.imageUri.value}")
                        navController.navigateUp()
                    },
                color = Color.White,
                text = "save"
            )
        }
    }


//    }
}