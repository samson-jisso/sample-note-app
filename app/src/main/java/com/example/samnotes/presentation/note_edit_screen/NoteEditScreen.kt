package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.samnotes.presentation.note_edit_screen.component.NoteTextHolderComponent
import com.example.samnotes.presentation.note_edit_screen.component.TopBar

@Composable
fun NoteEditScreen(
    viewModel: NoteScreenViewModel = hiltViewModel()
) {
    val padding = 8.dp
//    val state= viewModel.state.observeAsState()
    //update live data collect as state library
    val state = viewModel.state.value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
        )

        NoteTextHolderComponent(text = state.title,
            onValueChange = {
            viewModel.handleNoteEvent(NoteEvent.UpdateNoteTitle(it))
        },
            modifier = Modifier
                .padding(padding)
                .wrapContentHeight()
                .fillMaxWidth()
        )


        NoteTextHolderComponent(text = state.content,
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