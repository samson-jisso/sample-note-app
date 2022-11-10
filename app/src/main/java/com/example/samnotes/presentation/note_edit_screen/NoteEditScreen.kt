package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.samnotes.presentation.note_edit_screen.component.NoteTextHolderComponent
import com.example.samnotes.presentation.note_edit_screen.component.TopBar

@Composable
fun NoteEditScreen(
    navHostController: NavHostController,
    viewModel: NoteEditScreenViewModel = hiltViewModel()
) {
    val padding = 8.dp
    val state = viewModel.state.observeAsState()
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colors.onBackground),
                openCamera = {
                    /*TODO*/
                },
                navHostController = navHostController
            )

            NoteTextHolderComponent(
                text = state.value?.title ?: "please",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEvent.UpdateNoteTitle(it))
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )


            NoteTextHolderComponent(
                text = state.value?.content ?: "content",
                onValueChange = {
                    viewModel.handleNoteEvent(NoteEvent.UpdateNoteContent(it))
                },
                modifier = Modifier
                    .padding(top = 0.dp, bottom = padding, start = padding, end = padding)
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
        }
    }
}