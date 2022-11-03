package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.samnotes.features.data.local.entity.NoteEvent
import com.example.samnotes.presentation.note_screen.NoteScreenViewModel
import com.example.samnotes.presentation.note_screen.NoteState

@Composable
fun NoteEditScreen(
    viewModel: NoteScreenViewModel  = hiltViewModel()
) {
    val padding = 8.dp
    val state= viewModel.state
    //update live data collect as state library
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp),
            content = {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                       modifier = Modifier
                           .padding(padding)
                    ) {
                        Text(text = "back")
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .padding(padding)
                    ) {
                        Text(text = "search")
                    }
                }
            }
        )

        BasicTextField(
            value = state.value?.title ?: "",
            onValueChange = {
                viewModel.handleNoteEvent(noteEvent = NoteEvent.UpdateNoteTitle(it))
            },

            modifier = Modifier
                .padding(padding)
                .wrapContentHeight()
                .background(Color.White),
            decorationBox = {
                innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    innerTextField()
                }
            },
            cursorBrush = SolidColor(Color.Black),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )


    }
}