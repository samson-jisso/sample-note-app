package com.example.samnotes.presentation.note_edit_screen

import android.Manifest
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.navigation.Screen
import com.example.samnotes.presentation.note_edit_screen.component.NoteTextHolderComponent
import com.example.samnotes.presentation.note_edit_screen.component.TopBar

@Composable
fun NoteEditScreen(
    navHostController: NavHostController,
    viewModel: NoteEditScreenViewModel = hiltViewModel()
) {

//    val context = LocalContext.current
    val padding = 8.dp
    val state = viewModel.state.observeAsState()
    val photoUri: Uri? = viewModel.photoUri.value
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            navHostController.navigate(
                Screen.CameraView.route + "?noteId=${viewModel.noteId}"
            ){
                popUpTo(Screen.CameraView.route){
                    inclusive = true
                }
            }
        }
    }

    Surface(
        color = MaterialTheme.colors.background
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topBar, title, content, image) = createRefs()
            TopBar(
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colors.onBackground)
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                    },
                onClickCameraIcon = {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                },
                navHostController = navHostController
            )

            NoteTextHolderComponent(
                text = state.value?.title ?: "Enter Title",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEvent.UpdateNoteTitle(it))
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            if (photoUri != null) {
                Image(
                    painter = rememberImagePainter(data = photoUri),
                    contentDescription = "imageCaptured",
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(title.bottom)
                            start.linkTo(title.start)
                            height = Dimension.value(300.dp)
                            width = Dimension.value(150.dp)
                        }
                )
            }


            NoteTextHolderComponent(
                text = state.value?.content ?: "Enter content",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEvent.UpdateNoteContent(it))
                },
                modifier = Modifier
                    .padding(top = 0.dp, bottom = padding, start = padding, end = padding)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .constrainAs(content) {
                        if (photoUri != null) {
                            top.linkTo(image.bottom)
                        } else {
                            top.linkTo(title.bottom)
                        }
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}