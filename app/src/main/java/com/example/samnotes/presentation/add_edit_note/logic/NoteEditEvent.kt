package com.example.samnotes.presentation.add_edit_note.logic

import androidx.compose.ui.focus.FocusState

sealed class NoteEditEvent {
    data class EnteredTitle(val value:String) : NoteEditEvent()
    data class ChangedTitleFocus(val focusState: FocusState): NoteEditEvent()
    data class EnteredContent(val value: String) : NoteEditEvent()
    data class  ChangedContentFocus(val focusState: FocusState): NoteEditEvent()
    object  SaveNote: NoteEditEvent()
}