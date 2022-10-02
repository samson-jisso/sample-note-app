package com.example.samnotes.presentation.add_edit_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.samnotes.presentation.add_edit_note.components.NoteEditComponent
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditEvent
import com.example.samnotes.presentation.add_edit_note.logic.NoteEditViewModel
import com.example.samnotes.ui.theme.Chartreuse
import com.example.samnotes.ui.theme.LightBlue
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NoteEditScreen(
    navController: NavController,
    viewModel: NoteEditViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true ) {
        viewModel.eventFlow.collectLatest{ event ->
            when(event) {
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
                onClick = { viewModel.onEvent(NoteEditEvent.SaveNote) },
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
                .padding(paddingValues).padding(16.dp)
        ) {
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