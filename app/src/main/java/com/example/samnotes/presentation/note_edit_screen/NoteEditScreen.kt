package com.example.samnotes.presentation.note_edit_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteEditScreen() {
    val padding = 8.dp
    var title by remember {
        mutableStateOf(TextFieldValue(""))
    }
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
            value = title,
            onValueChange = {
                title = it
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