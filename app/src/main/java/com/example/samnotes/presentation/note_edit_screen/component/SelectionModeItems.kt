package com.example.samnotes.presentation.note_edit_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.samnotes.presentation.note_screen.NoteScreenEvent
import com.example.samnotes.presentation.note_screen.NoteScreenViewModel

@Composable
fun SelectionModeItems(
    viewModel: NoteScreenViewModel,
    modifier: Modifier,
    count: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "$count selected",
            color = MaterialTheme.colors.primary,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )
        IconButton(
            onClick = { viewModel.noteScreenEvent(NoteScreenEvent.DeleteSelectedNotes) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete",
                tint = MaterialTheme.colors.primary
            )
        }
    }

}