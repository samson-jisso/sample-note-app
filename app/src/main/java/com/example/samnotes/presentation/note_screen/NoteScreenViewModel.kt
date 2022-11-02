package com.example.samnotes.presentation.note_screen

import androidx.lifecycle.ViewModel
import com.example.samnotes.features.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel
@Inject
constructor(
   private val noteUseCases: NoteUseCases
):ViewModel() {
    private var _title = MutableSharedFlow<NoteState>()
    val title:SharedFlow<NoteState> = _title
    fun saveNote() {

    }
}