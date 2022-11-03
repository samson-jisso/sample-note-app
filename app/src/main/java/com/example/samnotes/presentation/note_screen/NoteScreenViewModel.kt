package com.example.samnotes.presentation.note_screen

import androidx.lifecycle.*
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.features.data.local.entity.NoteEvent
import com.example.samnotes.features.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel
@Inject
constructor(
   private val noteUseCases: NoteUseCases,
   private val savedStateHandle: SavedStateHandle
):ViewModel() {
    private var _state = MutableLiveData<NoteState.NoteData>()
    val state:LiveData<NoteState.NoteData> = _state

    private var noteId:Int? = null

    init {
        // retrieve from db then initialize _state
    }
    fun handleNoteEvent(
        noteEvent: NoteEvent
    ) = viewModelScope.launch {
        when(noteEvent) {
            is NoteEvent.UpdateNoteTitle -> {
                _state.value = state.value?.copy(
                    title = noteEvent.titleEntered ?: "please enter title"
                )
                noteUseCases.insertNote(
                    NoteEntity(

                    )
                )
            }
            is NoteEvent.UpdateNoteContent -> {
                _state.value = state.value?.copy(
                    content = noteEvent.contentEntered ?: "please enter title"
                )
            }

        }
    }
}