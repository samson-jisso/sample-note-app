package com.example.samnotes.presentation.notes.logic

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samnotes.features_notes.domain.model.Note
import com.example.samnotes.features_notes.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _showDialogState = MutableStateFlow(true)
    val showDialogState:StateFlow<Boolean> = _showDialogState.asStateFlow()


    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    fun onOpenDialogClicked() {
        _showDialogState.value = true
    }
    fun ClosePermissionDialog() {
        _showDialogState.value = false
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.GetNotes -> {
                getNotes()
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                }
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(note = event.note)
                }
                recentlyDeletedNote = event.note
            }
        }
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes()
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes
                )
            }.launchIn(viewModelScope)
    }
}