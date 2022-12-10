package com.example.samnotes.presentation.note_edit_screen

import android.content.Context
import android.net.Uri


sealed class NoteEditScreenEvent {
    data class UpdateNoteTitleEditScreen(val titleEntered: String = "Enter title") :
        NoteEditScreenEvent()
    data class UpdateNoteContentEditScreen(val contentEntered: String = "Enter Content") :
        NoteEditScreenEvent()
    object ClearTitleHint : NoteEditScreenEvent()
    object ClearContentHint : NoteEditScreenEvent()
    object TitleFocusChanged : NoteEditScreenEvent()
    object ContentFocusChanged : NoteEditScreenEvent()
    data class ShowUriImage(val uri: Uri, val context: Context) : NoteEditScreenEvent()
}
