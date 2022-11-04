package com.example.samnotes.presentation.note_edit_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samnotes.presentation.note_edit_screen.NoteEvent

@Composable
fun NoteTextHolderComponent(
     text: String,
    onValueChange:(String) -> Unit,
     modifier: Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        decorationBox = {
                innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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