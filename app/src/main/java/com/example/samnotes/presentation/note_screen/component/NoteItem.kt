package com.example.samnotes.presentation.note_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samnotes.presentation.note_screen.NoteScreenViewModel
import com.example.samnotes.ui.theme.Shapes

@Composable
fun NoteItem(
    modifier: Modifier,
    title: String?,
    content: String?,
    viewModel: NoteScreenViewModel,
    id: Int,
    onNavigateToNoteEditScreen: (Int) -> Unit,
    longPress: (Int) -> Unit,
    click: (Int) -> Unit
) {

    Column(

    ) {
        Card(
            backgroundColor = MaterialTheme.colors.onBackground,
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            if (viewModel.state.value?.clicked!!) {
                                onNavigateToNoteEditScreen(id)
                            } else {
                                click(id)
                            }
                        }, onLongPress = {
                            longPress(id)
                        }
                    )
                }
                .background(shape = Shapes.medium, color = MaterialTheme.colors.onBackground),
            elevation = 8.dp,
        ) {
            Text(
                text = content ?: "no content",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(18.dp)
            )
        }
        Text(
            text = "$title",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 8.dp),
        )
    }
}
