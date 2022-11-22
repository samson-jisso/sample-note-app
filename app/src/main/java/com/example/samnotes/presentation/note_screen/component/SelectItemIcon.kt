package com.example.samnotes.presentation.note_screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectItemIcon(
    modifier: Modifier
) {
    Icon(
        modifier = modifier
            .padding(4.dp),
        imageVector = Icons.Default.CheckCircle,
        contentDescription = "Select Icon",
        tint = MaterialTheme.colors.primary
    )
}