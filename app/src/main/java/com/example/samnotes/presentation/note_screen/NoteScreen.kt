package com.example.samnotes.presentation.note_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.samnotes.presentation.navigation.Screen
import com.example.samnotes.presentation.note_screen.component.NoteItem

@Composable
fun NotesScreen(
    navHostController: NavHostController, viewModel: NoteScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getNotes()
    })
    val noteScreenState by viewModel.state.observeAsState()

    BackHandler {
        viewModel.noteScreenEvent(
            NoteScreenEvent.LongPressClicked(
                clickable = true
            )
        )
        viewModel.noteScreenEvent(NoteScreenEvent.BackPressed)

    }
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, floatingActionButton = {
        FloatingActionButton(onClick = {
            navHostController.navigate(Screen.NotesEditScreen.route)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
        }
    }, floatingActionButtonPosition = FabPosition.End, content = { paddingValues ->
        Column {
            Text(
                text = "Notes",
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(4.dp),
                fontSize = 32.sp,
                color = MaterialTheme.colors.primary

            )

            LazyVerticalGrid(columns = GridCells.Adaptive(178.dp),

                // content padding
                contentPadding = PaddingValues(
                    start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
                ), content = {
                    items(items = noteScreenState?.data ?: listOf()) { item ->
                        if (item.id != null) {
                            Box(
                                content = {
                                    NoteItem(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(100.dp)
                                            .padding(4.dp),
                                        title = item.title?.take(20),
                                        content = item.content?.take(40),
                                        id = item.id,
                                        navHostController = navHostController,
                                        viewModel = viewModel,
                                        onClick = { noteId ->
                                            viewModel.noteScreenEvent(
                                                NoteScreenEvent.DeselectNote(
                                                    noteId
                                                )
                                            )
                                        },
                                        onLongPress = { noteId ->
                                            viewModel.noteScreenEvent(
                                                NoteScreenEvent.LongPressClicked(
                                                    clickable = false
                                                )
                                            )
                                            viewModel.noteScreenEvent(
                                                NoteScreenEvent.SelectNote(noteId)
                                            )
                                        }
                                    )
//                                    Box(
//                                        modifier = Modifier
//                                            .align(Alignment.TopEnd)
//                                    ) {
//                                   Box(modifier = Modifier
//                                       .size(20.dp)
//                                       .clip(CircleShape)
//                                       .background(Color.Red)
////                                       .align(Alignment.TopEnd)
//                                   )
                                    if (noteScreenState?.selectedNoteId?.contains(item.id)!!) {
                                        Icon(
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .padding(4.dp),
                                            imageVector = Icons.Default.CheckCircle,
                                            contentDescription = "Select Icon",
                                            tint = MaterialTheme.colors.primary
                                        )

                                    }
//                                    }
                                }
                            )
                        }
                    }
                })
        }
    })
}