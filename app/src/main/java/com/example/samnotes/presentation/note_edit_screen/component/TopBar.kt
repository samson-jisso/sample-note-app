package com.example.samnotes.presentation.note_edit_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TopBar(
    navHostController: NavHostController,
    modifier: Modifier, openCamera: () -> Unit
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
                    navHostController.navigateUp()
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

            Card(
                modifier = modifier
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    content = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "back arrow",
                        tint = MaterialTheme.colors.primary
                    )
                })

            }
            Card(
                modifier = modifier
            ) {
                IconButton(onClick = {
                    openCamera()
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