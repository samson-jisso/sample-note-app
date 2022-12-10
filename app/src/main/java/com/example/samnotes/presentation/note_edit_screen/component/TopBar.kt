package com.example.samnotes.presentation.note_edit_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TopBar(
    onNavNoteScreen:() -> Unit,
    modifier: Modifier,
    onClickCameraIcon: () -> Unit,
    onFileAddClicked: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxWidth()
        , content = {
        Card(
            modifier = modifier
                .align(Alignment.TopStart)
        ) {
            IconButton(
                onClick = {
                   onNavNoteScreen()
                }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back arrow",
                tint = MaterialTheme.colors.primary
            )

            }
        }
        Row(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
        //add file button
            Card(
                modifier = modifier
            ) {
                IconButton(
                    onClick = { onFileAddClicked() },
                    content = {
                    Icon(
                        imageVector = Icons.Default.Link,
                        contentDescription = "Search",
                        tint = MaterialTheme.colors.primary
                    )
                })

            }
            //AddPhoto button
            Card(
                modifier = modifier
            ) {
                IconButton(
                    onClick = {
                    onClickCameraIcon()
                }, content = {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "Add Photo",
                        tint = MaterialTheme.colors.primary
                    )
                })
            }
        }
    })
}