package com.example.samnotes.presentation.add_edit_note

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.samnotes.presentation.add_edit_note.components.NoteEditComponent
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditEvent
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditState
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditViewModel
import com.example.samnotes.presentation.util.Screen
import com.example.samnotes.ui.theme.LightBlue
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

var photoUri: String? = null


@Composable
fun NoteEditScreen(
    arg: String?,
    navController: NavController,
    viewModel: NoteEditViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val requestCameraPermission =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navController.navigate(
                    Screen.CameraView.route
                )
            } else {
                coroutineScope.launch {
                    val snackBar = scaffoldState.snackbarHostState.showSnackbar(
                        message = "camera permission denied",
                        actionLabel = "Settings",
                        duration = SnackbarDuration.Short
                    )
                    if (snackBar == SnackbarResult.ActionPerformed) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", "com.example.samnotes", null)
                        }.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }

                }

            }
        }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is NoteEditViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is NoteEditViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteEditEvent.SaveNote)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LightBlue)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row() {
                NoteEditComponent(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(NoteEditEvent.EnteredTitle(it))
                    }, onFocusChange = {
                        viewModel.onEvent(NoteEditEvent.ChangedTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable {
                                requestCameraPermission.launch(Manifest.permission.CAMERA)
                            }
                    )
                }
            }
            if (arg !== null || viewModel.photoUri.value.photoUri.isNotBlank()) {

                arg?.let {
                    viewModel.photoUri.value = NoteEditState(
                        photoUri = arg
                    )
                    photoUri = it
                }
                LaunchedEffect(key1 = true) {
                    photoUri = viewModel.photoUri.value.photoUri
                }
                println(photoUri)

                Image(
                    painter = rememberImagePainter(data = photoUri),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            NoteEditComponent(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(NoteEditEvent.EnteredContent(it))
                }, onFocusChange = {
                    viewModel.onEvent(NoteEditEvent.ChangedContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }

    }

}
