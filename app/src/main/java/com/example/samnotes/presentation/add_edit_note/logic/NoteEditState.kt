package com.example.samnotes.presentation.add_edit_note.logic

import android.net.Uri

data class NoteEditState(
    val text:String = "",
    val hint:String = "",
    var photoUri:String = "",
    val isHintVisible: Boolean = true
)
