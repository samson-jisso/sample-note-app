package com.example.samnotes.presentation.note_edit_screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.samnotes.presentation.camera_view.logic.CameraViewModel
import com.example.samnotes.presentation.camera_view.presentation.CameraEvent
import com.example.samnotes.presentation.note_edit_screen.component.NoteTextHolderComponent
import com.example.samnotes.presentation.note_edit_screen.component.TopBar

@Composable
fun NoteEditScreen(
    onNavigateToCamera: (Int) -> Unit,
    onNavigateToNoteScreen: () -> Unit,
    viewModel: NoteEditScreenViewModel = hiltViewModel(),
    cameraViewModel: CameraViewModel = hiltViewModel()
) {
    val padding = 4.dp
    val state by viewModel.state.observeAsState()
    val context = LocalContext.current
    val d = LocalDensity.current
    var offsetX by remember {
        mutableStateOf(viewModel.offsetX.value)
    }
    var offsetY by remember {
        mutableStateOf(viewModel.offsetY.value)
    }
    if (viewModel.offsetX.value != 0f && viewModel.offsetY.value != 0f) {
        offsetX = viewModel.offsetX.value
        offsetY = viewModel.offsetY.value
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.noteId?.let { noteId ->
                onNavigateToCamera(noteId)
            }
        }
    }
    val getFile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { nullableUri ->
        if (nullableUri != null) {
            viewModel.handleNoteEvent(NoteEditScreenEvent.ShowUriImage(nullableUri, context))
        }
    }

    Surface(
        color = MaterialTheme.colors.background
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (topBar, title, content, ImageSection) = createRefs()
            TopBar(
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colors.background)
                    .constrainAs(topBar) {
                        top.linkTo(parent.top)
                    },
                onClickCameraIcon = {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                },
                onNavNoteScreen = {
                    onNavigateToNoteScreen()
                },
                onFileAddClicked = {
                    getFile.launch("*/*")
                }
            )

            NoteTextHolderComponent(
                text = state?.title ?: "Enter Title",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEditScreenEvent.UpdateNoteTitleEditScreen(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(title) {
                        top.linkTo(topBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints

                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            viewModel.handleNoteEvent(NoteEditScreenEvent.ClearTitleHint)
                        } else {
                            viewModel.handleNoteEvent(NoteEditScreenEvent.TitleFocusChanged)
                        }
                    }
            )
            Row(modifier = Modifier
                .constrainAs(ImageSection) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }) {
                if (state?.photoUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = state?.photoUri),
                        contentDescription = "Image Captured",
                        modifier = Modifier
                            .offset(
                                (offsetX / d.density).dp,
                                (offsetY / d.density).dp
                            )
                            .padding(8.dp)
                            .height(300.dp)
                            .width(150.dp)
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    offsetX += dragAmount.x
                                    offsetY += dragAmount.y
                                    cameraViewModel.handleEvent(
                                        CameraEvent.OnUpdate(
                                            pictureAddress = state?.photoUri.toString(),
                                            offsetX = offsetX,
                                            offsetY = offsetY
                                        )
                                    )
                                }
                            }

                    )
                }
                if (state?.showFileUriImage == true && state?.fileUriImage != null) {
                    AsyncImage(
                        model = state?.fileUriImage,
                        contentDescription = "Image From File",
                        modifier = Modifier
                            .padding(8.dp)
                            .height(300.dp)
                            .width(150.dp)
                    )
                }

            }


            NoteTextHolderComponent(
                text = state?.content ?: "Enter content",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEditScreenEvent.UpdateNoteContentEditScreen(it))
                },
                modifier = Modifier
                    .padding(top = 0.dp, bottom = padding, start = padding, end = padding)
                    .fillMaxWidth()
                    .constrainAs(content) {
                        if (state?.photoUri != null) {
                            top.linkTo(ImageSection.bottom)
                        } else {
                            top.linkTo(title.bottom)
                        }
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            viewModel.handleNoteEvent(NoteEditScreenEvent.ClearContentHint)
                        } else {
                            viewModel.handleNoteEvent(NoteEditScreenEvent.ContentFocusChanged)
                        }
                    }
            )
        }
    }
}