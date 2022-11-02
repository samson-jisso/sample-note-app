package com.example.samnotes.presentation.note_screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
) {
    Card(
        backgroundColor = Color.Red,
        modifier = Modifier
            .padding(4.dp),
        elevation = 8.dp,
    ) {
        Text(
            text = "hello",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}