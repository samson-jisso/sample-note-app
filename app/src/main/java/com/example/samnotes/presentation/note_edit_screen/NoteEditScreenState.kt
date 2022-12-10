package com.example.samnotes.presentation.note_edit_screen

import android.net.Uri

data class NoteEditScreenState(
    val id: Int?,
    val title: String?,
    val content: String?,
    val photoUri: Uri? = null,
    val fileUriImage: Uri? = null,
    var showFileUriImage: Boolean = false,
)

