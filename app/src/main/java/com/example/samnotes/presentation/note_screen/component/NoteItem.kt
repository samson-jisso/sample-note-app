package com.example.samnotes.presentation.note_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteItem(
    modifier: Modifier,
    title: String?,
    content: String?
) {

        Column(

        ) {
            Card(
                backgroundColor = MaterialTheme.colors.onBackground,
                modifier = modifier,
                elevation = 8.dp,
            ) {
            Text(
                text = content ?: "no content",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Justify,
            )
            }
            Text(
                text = title ?: "untitled",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(2.dp)
            )
        }
}