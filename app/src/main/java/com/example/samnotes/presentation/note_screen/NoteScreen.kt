package com.example.samnotes.presentation.note_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.samnotes.presentation.navigation.Screen
import com.example.samnotes.presentation.note_screen.component.NoteItem

@Composable
fun NotesScreen(
    navHostController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.NotesEditScreen.route)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            Column {
                Text(
                    text = "Notes",
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(4.dp),
                    fontSize = 32.sp,

                )
                val list = (1..10).map { it.toString() }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(178.dp),

                    // content padding
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                    content = {
                        items(list.size) { index ->
                            NoteItem(modifier = Modifier.fillMaxWidth())
                        }
                    }
                )
            }
        }
    )
}